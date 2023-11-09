package com.example.telegramstatistics.models;

import lombok.Data;

import java.util.List;

@Data
public class ChatHistory {

    private String name;
    private String type;
    private long id;
    private List<Message> messages;
}
