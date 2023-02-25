package com.example.capstoneproject.service.interfaces;

import com.example.capstoneproject.entity.Answer;
import com.example.capstoneproject.entity.Question;

import java.util.List;

public interface AnswerService {
    Answer addAnswer(Answer answer);
    List<Answer> getAllAnswer();
    List<Answer> getAllAnswerFalse();
    Answer getAnswerById(int id);
    List<Answer> getAnswerByQuestionId(Question quest);
    Answer updateAnswer(Answer answer);
    String deleteAnswerById(int id);
}
