package com.example.telegramstatistics.dao;

import com.example.telegramstatistics.models.ChatHistory;
import com.example.telegramstatistics.models.Message;
import com.example.telegramstatistics.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ChatHistoryDAOImpl implements ChatHistoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveChatHistory(ChatHistory chatHistory) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(chatHistory);
    }

    @Override
    public User getMostActiveUser() {
        Session currentSession = sessionFactory.getCurrentSession();
        String hql = "SELECT U " +
                "FROM User U " +
                "JOIN Message M ON U.userId = M.from_id " +
                "GROUP BY U " +
                "ORDER BY COUNT(M) DESC";

        return currentSession.createQuery(hql, User.class).setMaxResults(1).uniqueResult();
    }

    @Override
    public User getLeastActiveUser() {
        Session currentSession = sessionFactory.getCurrentSession();
        String sql = "SELECT * FROM users u " +
                "JOIN message m ON u.userId = m.from_id " +
                "GROUP BY u.userId " +
                "ORDER BY COUNT(u.userId) " +
                "LIMIT 1";

        return currentSession.createNativeQuery(sql, User.class).uniqueResult();
    }

    @Override
    public User getMostWritingUser() {
        Session currentSession = sessionFactory.getCurrentSession();

        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<Message> root = criteria.from( Message.class );
        Join<Message, User> userJoin = root.join("from_id", JoinType.INNER);

        criteria.select(userJoin)
                .groupBy(userJoin.get("userId"))
                .orderBy(builder.desc(builder.sum(builder.length(root.get("messageText")))))
                .having(builder.equal(userJoin.get("userId"), userJoin.get("from_id")));

        List<User> resultList = currentSession.createQuery(criteria)
                .setMaxResults(1)
                .getResultList();
        return !resultList.isEmpty() ? resultList.get(0) : null;
    }

    @Override
    public User getLeastWritingUser() {
        Session currentSession = sessionFactory.getCurrentSession();
        String sql = "SELECT * FROM users u " +
                "JOIN message m ON u.userId = m.from_id " +
                "GROUP BY u.userId " +
                "ORDER BY SUM(LENGTH(m.messageText)) " +
                "LIMIT 1";

        return currentSession.createNativeQuery(sql, User.class).uniqueResult();
    }
}
