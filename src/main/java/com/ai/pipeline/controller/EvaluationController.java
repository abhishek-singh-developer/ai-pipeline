package com.ai.pipeline.controller;


import com.ai.pipeline.model.Evaluation;
import com.ai.pipeline.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService service;

    @PostMapping("/{conversationId}")
    public Evaluation evaluate(@PathVariable String conversationId) {
        return service.evaluate(conversationId);
    }

    @GetMapping("/{conversationId}")
    public Evaluation get(@PathVariable String conversationId) {
        return service.get(conversationId);
    }
}