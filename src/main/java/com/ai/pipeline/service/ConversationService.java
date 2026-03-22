package com.ai.pipeline.service;

import com.ai.pipeline.model.Conversation;
import org.springframework.stereotype.Service;

import com.ai.pipeline.repository.ConversationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository repository;
    private final ObjectMapper objectMapper;
    
    public String save(String rawJson) {
        try {
            JsonNode root = objectMapper.readTree(rawJson);

            String conversationId = root.get("conversation_id").asText();
            String agentVersion = root.get("agent_version").asText();

            // Correct Builder Syntax:
            Conversation conversation = Conversation.builder()
                    .conversationId(conversationId)
                    .agentVersion(agentVersion)
                    .rawJson(rawJson)
                    .build();

            repository.save(conversation);

            return conversationId;

        } catch (Exception e) {
            throw new RuntimeException("Error processing conversation: " + e.getMessage(), e);
        }
    }
}