package com.example.telegramstatistics.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
public class Message {

    @Id
    private long id;
    private String type;
    private LocalDateTime date;
    @JsonProperty("from")
    private String from_user;
    private String from_id;
    private String messageText;

    @JsonSetter("date")
    public void setDateFromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        date = LocalDateTime.parse(dateString, formatter);
    }

    @JsonSetter("text_entities")
    public void setTextFromArray(List<TextEntity> textEntities) {
        StringBuilder sb = new StringBuilder();
        textEntities.forEach(e -> sb.append(e.getText()));
        messageText = sb.toString();
    }
}
