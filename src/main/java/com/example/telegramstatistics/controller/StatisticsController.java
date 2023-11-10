package com.example.telegramstatistics.controller;

import com.example.telegramstatistics.mapper.ChatHistoryMapper;
import com.example.telegramstatistics.models.ChatHistory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {

    private final ChatHistoryMapper mapper;

    public StatisticsController(ChatHistoryMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> getStatistics(@RequestParam() MultipartFile file) {
        try {
            ChatHistory chatHistory = mapper.mapJsonFileToObject(file.getInputStream(), ChatHistory.class);


            return ResponseEntity.ok("File processed successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error processing the JSON file");
        }
    }
}
