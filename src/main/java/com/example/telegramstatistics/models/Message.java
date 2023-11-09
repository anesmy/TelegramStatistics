package com.example.telegramstatistics.models;

import java.time.LocalDate;

public class Message {
    private long id;
    private String type;
    private LocalDate date;
    private String dateUnixtime;
    private String from;
    private long from_id;
    private String text;
}
