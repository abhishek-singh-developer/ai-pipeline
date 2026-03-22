package com.ai.pipeline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SuggestionService {

    public String generate(double response, double tool, double coherence, String issues) {

        List<String> suggestions = new ArrayList<>();

        if (tool < 0.8) {
            suggestions.add("Improve tool parameter extraction in prompt");
        }

        if (coherence < 0.8) {
            suggestions.add("Add context retention instructions");
        }

        if (issues.contains("latency")) {
            suggestions.add("Optimize tool execution latency");
        }

        return String.join(", ", suggestions);
    }
}