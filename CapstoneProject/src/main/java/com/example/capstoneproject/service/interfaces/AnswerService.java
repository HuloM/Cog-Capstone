package com.example.capstoneproject.service.interfaces;

import com.example.capstoneproject.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer addAnswer(Answer answer);
    List<Answer> getAllAnswer();
    List<Answer> getAllAnswerFalse();
    Answer getAnswerById(int id);
    Answer getAnswerByQuestionId(int id);
    Answer updateAnswer(Answer answer);
    String deleteAnswerById(int id);
}
