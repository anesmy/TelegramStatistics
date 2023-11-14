package com.example.telegramstatistics.service;


import com.example.telegramstatistics.models.ChatHistory;
import com.example.telegramstatistics.models.User;

public interface ChatHistoryService {
    void saveChatHistory(ChatHistory chatHistory);

    User getMostActiveUser();
    User getLeastActiveUser();
    User getMostWritingUser();
    User getLeastWritingUser();
}
