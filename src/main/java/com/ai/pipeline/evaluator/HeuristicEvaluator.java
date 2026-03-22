package com.ai.pipeline.evaluator;

import org.springframework.stereotype.Component;

@Component
public class HeuristicEvaluator {

    public String check(String json) {
        if (json.contains("latency_ms\": 1200")) {
            return "High latency detected";
        }
        return "No issues";
    }
}
