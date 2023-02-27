package com.example.capstoneproject.service.interfaces;

import com.example.capstoneproject.entity.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);
    List<Question> getAllQuestions();
    List<Question> getAllQuestionsFalse();
    Question getQuestionById(int id);
    List<Question> getQuestionByTopic(String topic);
    List<Question> getQuestionLikeTitle(String title);
    List<Question> getQuestionLikeTitleAndTopic(String title, String topic);
    Question updateQuestion(Question question);
    String deleteQuestionById(int id);
}
