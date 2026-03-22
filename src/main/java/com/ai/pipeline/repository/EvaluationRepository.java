package com.ai.pipeline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.pipeline.model.Evaluation;


public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByConversationId(String conversationId);
}