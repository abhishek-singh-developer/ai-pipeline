package com.ai.pipeline.evaluator;

import org.springframework.stereotype.Component;

@Component
public class ToolEvaluator {

    public double evaluate(String json) {
        if (json.contains("tool_name")) return 0.9;
        return 0.5;
    }
}