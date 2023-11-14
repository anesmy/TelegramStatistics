package com.example.telegramstatistics.models;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name="chat")
public class ChatHistory {

    @Id
    private long id;
    private String name;
    private String type;
    @OneToMany
    private List<Message> messages;
}
