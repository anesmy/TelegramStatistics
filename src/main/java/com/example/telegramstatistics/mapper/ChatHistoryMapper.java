package com.example.telegramstatistics.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ChatHistoryMapper {
    private final ObjectMapper objectMapper;

    public ChatHistoryMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public <T> T mapJsonFileToObject(InputStream inputStream, Class<T> valueType) throws IOException {
        return objectMapper.readValue(inputStream, valueType);
    }
}
