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

  Endpoint                Method   Description                         
| --------------------- | ------ | ----------------------------------- |
| `/conversations`      | POST   | Send message to AI and get response |
| `/evaluate/coherence` | POST   | Check quality/coherence of AI text  |
| `/evaluate/heuristic` | POST   | Heuristic based evaluator           |

API will be available at: http://localhost:8080
