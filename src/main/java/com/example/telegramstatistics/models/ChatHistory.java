package com.example.telegramstatistics.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import java.util.List;

@Data
@Entity
@Table(name="chat")
public class ChatHistory {

    @Id
    @JsonProperty("id")
    private long chatId;

    private String name;
    private String type;
    
    @OneToMany(mappedBy = "chatHistory", cascade = CascadeType.ALL)
    private List<Message> messages;
}
