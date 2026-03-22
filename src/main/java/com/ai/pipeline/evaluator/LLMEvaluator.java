package com.ai.pipeline.evaluator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LLMEvaluator {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public double evaluate(String conversationJson) {

        String prompt = """
        You are an AI evaluator.

        Evaluate the following conversation on:
        - Helpfulness (0 to 1)
        - Correctness (0 to 1)
        - Coherence (0 to 1)

        Return ONLY JSON like:
        {
          "helpfulness": 0.8,
          "correctness": 0.9,
          "coherence": 0.85
        }

        Conversation:
        """ + conversationJson;

        try {
            String response = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(Map.of(
                            "model", "gpt-4o-mini",
                            "messages", List.of(
                                    Map.of("role", "user", "content", prompt)
                            )
                    ))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JsonNode root = objectMapper.readTree(response);
            String content = root
                    .get("choices")
                    .get(0)
                    .get("message")
                    .get("content")
                    .asText();

            JsonNode scores = objectMapper.readTree(content);

            double helpfulness = scores.get("helpfulness").asDouble();
            double correctness = scores.get("correctness").asDouble();
            double coherence = scores.get("coherence").asDouble();

            return (helpfulness + correctness + coherence) / 3;

        } catch (Exception e) {
            return 0.5; // fallback
        }
    }
}