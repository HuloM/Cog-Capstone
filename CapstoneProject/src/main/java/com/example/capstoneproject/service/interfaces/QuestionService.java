package com.example.capstoneproject.service.interfaces;

import com.example.capstoneproject.entity.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);
    List<Question> getAllQuestions();
    List<Question> getAllQuestionsFalse();
    Question getQuestionById(int id);
    List<Question> getQuestionByTopic(String topic);
    Question updateQuestion(Question question);
    String deleteQuestionById(int id);
}
