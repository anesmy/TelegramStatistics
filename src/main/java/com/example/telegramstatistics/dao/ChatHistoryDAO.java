package com.example.telegramstatistics.dao;

import com.example.telegramstatistics.models.ChatHistory;
import com.example.telegramstatistics.models.User;

public interface ChatHistoryDAO {

    void saveChatHistory(ChatHistory chatHistory);
    User getMostActiveUser();

    User getLeastActiveUser();
    User getMostWritingUser();
    User getLeastWritingUser();
}
