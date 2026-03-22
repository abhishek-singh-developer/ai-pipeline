package com.ai.pipeline.evaluator;

import org.springframework.stereotype.Component;

@Component
public class CoherenceEvaluator {

    public double evaluate(String json) {
        if (json.length() > 100) return 0.8;
        return 0.6;
    }
}