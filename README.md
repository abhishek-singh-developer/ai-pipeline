A full‑stack AI Pipeline application built with Spring Boot that provides REST APIs for managing AI‑driven conversations and evaluations. The project is designed to serve as a backend for building AI‑powered services, wrapping AI integrations (like OpenAI) with evaluation logic to automate responses and score conversations.

| Technology  | Purpose                              |
| ----------- | ------------------------------------ |
| Java        | Core language                        |
| Spring Boot | Web framework & dependency injection |
| Maven       | Build & dependency management        |
| Docker      | Containerization                     |
| REST APIs   | Service endpoints                    |

Features :

>> Spring Boot Java Application
Starts a REST API service and exposes endpoints for AI interaction and evaluation.

>> Conversation APIs
APIs to send prompts and get generated responses from AI models.

>> Evaluation Endpoints
Routes to evaluate AI responses using multiple evaluator strategies (e.g., coherence, heuristic).

>> Modular Design
Split into controllers, services, evaluators, and repositories for clean architecture.

>> Docker Support
Dockerfile included so you can containerize and run the application easily.

API Endpoints : 

| Endpoint              | Method | Description                         |
| --------------------- | ------ | ----------------------------------- |
| `/conversations`      | POST   | Send message to AI and get response |
| `/evaluate/coherence` | POST   | Check quality/coherence of AI text  |
| `/evaluate/heuristic` | POST   | Heuristic based evaluator           |

API will be available at: http://localhost:8080


## Architecture

The pipeline consists of the following modules:


**Modules:**

1. **Data Ingestion Layer**  
   - Ingests multi-turn conversation logs with full context.  
   - Processes feedback signals: user ratings, ops annotations, human labels.  
   - Supports batch and real-time ingestion (~1000+ conversations/minute).  

2. **Evaluation Framework**  
   - **LLM-as-Judge**: Assesses response quality, helpfulness, factuality.  
   - **Tool Call Evaluator**: Checks tool selection, parameter accuracy, execution success.  
   - **Multi-turn Coherence Evaluator**: Ensures context consistency across conversation turns.  
   - **Heuristic Evaluator**: Performs format compliance, latency checks, and required field validations.  

3. **Feedback Integration**  
   - Aggregates annotations from multiple human annotators.  
   - Handles annotator disagreement and applies weighted evaluations.  
   - Supports confidence-based routing (auto-label vs human review).  

4. **Self-Updating Mechanism**  
   - Detects failure patterns in prompts and tools.  
   - Suggests prompt improvements and tool schema fixes.  
   - Generates improvement suggestions with rationale and expected impact.  

---

## Features

- Multi-turn conversation ingestion  
- Automated evaluation using multiple evaluator types  
- Detection of tool misuse or hallucinated parameters  
- Coherence checks for long conversations  
- Feedback integration and disagreement handling  
- Automatic improvement suggestions for prompts and tools  
- Scalable architecture suitable for high throughput  

---

## Setup Instructions

1. **Clone the repository**
```bash
git clone https://github.com/abhishek-singh-developer/ai-pipeline.git
cd ai-pipeline

Sample Conversation JSON
{
  "conversation_id": "conv_abc123",
  "agent_version": "v2.3.1",
  "turns": [
    {
      "turn_id": 1,
      "role": "user",
      "content": "I need to book a flight to NYC next week",
      "timestamp": "2024-01-15T10:30:00Z"
    },
    {
      "turn_id": 2,
      "role": "assistant",
      "content": "I'd be happy to help you book a flight to NYC...",
      "tool_calls": [
        {
          "tool_name": "flight_search",
          "parameters": {
            "destination": "NYC",
            "date_range": "2024-01-22/2024-01-28"
          },
          "result": {"status": "success", "flights": ["..."]},
          "latency_ms": 450
        }
      ],
      "timestamp": "2024-01-15T10:30:02Z"
    }
  ],
  "feedback": {
    "user_rating": 4,
    "ops_review": {"quality": "good", "notes": "Correct tool usage"},
    "annotations": [{"type": "tool_accuracy", "label": "correct", "annotator_id": "ann_001"}]
  },
  "metadata": {"total_latency_ms": 1200, "mission_completed": true}
}

Sample Evaluation Output
{
  "evaluation_id": "eval_xyz789",
  "conversation_id": "conv_abc123",
  "scores": {
    "overall": 0.87,
    "response_quality": 0.90,
    "tool_accuracy": 0.95,
    "coherence": 0.85
  },
  "tool_evaluation": {
    "selection_accuracy": 1.0,
    "parameter_accuracy": 0.95,
    "execution_success": true
  },
  "issues_detected": [
    {"type": "latency", "severity": "warning", "description": "Response latency 1200ms exceeds 1000ms target"}
  ],
  "improvement_suggestions": [
    {"type": "prompt", "suggestion": "Add explicit date format instruction", "rationale": "Reduce date inference errors", "confidence": 0.72}
  ]
}
