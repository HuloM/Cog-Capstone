package com.example.capstoneproject.service;

import com.example.capstoneproject.entity.Question;
import com.example.capstoneproject.repository.QuestionRepository;
import com.example.capstoneproject.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository repository;

    @Override
    public Question addQuestion(Question question) {
        return repository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return (List<Question>) repository.findAll();
    }

    @Override
    public List<Question> getAllQuestionsFalse() {
        return  repository.findAllByIsFalse();
    }

    @Override
    public Question getQuestionById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Question> getQuestionByTopic(String topic) {
        return repository.findByTopic(topic);
    }

    @Override
    public Question updateQuestion(Question question) {
        return repository.save(question);
    }

    @Override
    public String deleteQuestionById(int id) {
        repository.deleteById(id);
        return "Question removed with id: " + id;
    }
}
