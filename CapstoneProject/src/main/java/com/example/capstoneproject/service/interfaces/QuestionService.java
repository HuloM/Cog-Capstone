package com.example.capstoneproject.service.interfaces;

import com.example.capstoneproject.entity.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);
    List<Question> getAllQuestions();
    List<Question> getAllQuestionsFalse();
    Question getQuestionById(Long id);
    Question getQuestionByTopic(String topic);
    Question updateQuestion(Question question);
    String deleteQuestionById(Long id);
}
