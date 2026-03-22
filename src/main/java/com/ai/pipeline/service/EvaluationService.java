package com.ai.pipeline.service;

import com.ai.pipeline.evaluator.CoherenceEvaluator;
import com.ai.pipeline.evaluator.HeuristicEvaluator;
import com.ai.pipeline.evaluator.LLMEvaluator;
import com.ai.pipeline.evaluator.ToolEvaluator;
import com.ai.pipeline.model.Conversation;
import com.ai.pipeline.model.Evaluation;
import com.ai.pipeline.repository.ConversationRepository;
import com.ai.pipeline.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final ConversationRepository conversationRepo;
    private final EvaluationRepository evaluationRepo;

    private final LLMEvaluator llmEvaluator;
    private final ToolEvaluator toolEvaluator;
    private final CoherenceEvaluator coherenceEvaluator;
    private final HeuristicEvaluator heuristicEvaluator;
    private final SuggestionService suggestionService;

    public Evaluation evaluate(String conversationId) {

        Conversation convo = conversationRepo
                .findByConversationId(conversationId)
                .orElseThrow();

        String json = convo.getRawJson();

        double responseScore = llmEvaluator.evaluate(json);
        double toolScore = toolEvaluator.evaluate(json);
        double coherenceScore = coherenceEvaluator.evaluate(json);
        String issues = heuristicEvaluator.check(json);

        double overall = (responseScore + toolScore + coherenceScore) / 3;

        String suggestions = suggestionService.generate(
                responseScore, toolScore, coherenceScore, issues
        );

        Evaluation eval = new Evaluation();
        eval.setConversationId(conversationId);
        eval.setResponseQuality(responseScore);
        eval.setToolAccuracy(toolScore);
        eval.setCoherence(coherenceScore);
        eval.setOverallScore(overall);
        eval.setIssues(issues);
        eval.setSuggestions(suggestions);

        return evaluationRepo.save(eval);
    }

    public Evaluation get(String conversationId) {
        return evaluationRepo.findByConversationId(conversationId).orElseThrow();
    }
}