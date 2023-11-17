package com.example.telegramstatistics.controller;

import com.example.telegramstatistics.mapper.ChatHistoryMapper;
import com.example.telegramstatistics.models.ChatHistory;
import com.example.telegramstatistics.models.Message;
import com.example.telegramstatistics.models.User;
import com.example.telegramstatistics.service.ChatHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {

    private final ChatHistoryMapper mapper;
    private final ChatHistoryService service;

    public StatisticsController(ChatHistoryMapper mapper, ChatHistoryService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> getStatistics(@RequestParam() MultipartFile file) {
        try {
            ChatHistory chatHistory = mapper.mapJsonFileToObject(file.getInputStream(), ChatHistory.class);

            chatHistory.getMessages().forEach(m -> m.setChatHistory(chatHistory));
            service.saveChatHistory(chatHistory);

            return ResponseEntity.ok("File processed successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error processing the JSON file");
        }
    }

    @GetMapping("/users/most-active")
    public ResponseEntity<User> getMostActiveUser() {
        User user = service.getMostActiveUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users/least-active")
    public ResponseEntity<User> getLeastActiveUser() {
        User user = service.getLeastActiveUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users/most-wrote")
    public ResponseEntity<User> getMostWroteUser() {
        User user = service.getMostWritingUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users/least-wrote")
    public ResponseEntity<User> getLeastWroteUser() {
        User user = service.getLeastWritingUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
