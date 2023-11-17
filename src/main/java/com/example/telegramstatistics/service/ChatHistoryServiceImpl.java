package com.example.telegramstatistics.service;

import com.example.telegramstatistics.dao.ChatHistoryDAO;
import com.example.telegramstatistics.models.ChatHistory;
import com.example.telegramstatistics.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    @Autowired
    private ChatHistoryDAO chatHistoryDAO;

    @Transactional
    @Override
    public void saveChatHistory(ChatHistory chatHistory) {
        chatHistoryDAO.saveChatHistory(chatHistory);
    }

    @Transactional
    @Override
    public User getMostActiveUser() {
        return chatHistoryDAO.getMostActiveUser();
    }

    @Transactional
    @Override
    public User getLeastActiveUser() {
        return chatHistoryDAO.getLeastActiveUser();
    }

    @Transactional
    @Override
    public User getMostWritingUser() {
        return chatHistoryDAO.getMostWritingUser();
    }

    @Transactional
    @Override
    public User getLeastWritingUser() {
        return chatHistoryDAO.getLeastWritingUser();
    }
}
