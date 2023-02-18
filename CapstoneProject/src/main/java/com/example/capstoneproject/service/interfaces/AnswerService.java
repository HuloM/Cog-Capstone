package com.example.capstoneproject.service.interfaces;

import com.example.capstoneproject.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer addAnswer(Answer answer);
    List<Answer> getAllAnswer();
    List<Answer> getAllAnswerFalse();
    Answer getAnswerById(Long id);
    Answer getAnswerByQuestionId(Long id);
    Answer updateAnswer(Answer answer);
    String deleteAnswerById(Long id);
}
