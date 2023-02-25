package com.example.capstoneproject.service;

import com.example.capstoneproject.entity.Answer;
import com.example.capstoneproject.entity.Question;
import com.example.capstoneproject.repository.AnswerRepository;
import com.example.capstoneproject.service.interfaces.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository repository;

    @Override
    public Answer addAnswer(Answer answer) {
        return repository.save(answer);
    }

    @Override
    public List<Answer> getAllAnswer() {
        return (List<Answer>) repository.findAll();
    }

    @Override
    public List<Answer> getAllAnswerFalse() {
        return repository.findAllByIsFalse();
    }

    @Override
    public Answer getAnswerById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Answer> getAnswerByQuestionId(Question quest) {
        return repository.findByQuestionId(quest);
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        return repository.save(answer);
    }

    @Override
    public String deleteAnswerById(int id) {
        repository.deleteById(id);
        return "Answer removed with id: " + id;
    }
}
