package com.example.capstoneproject.repository;

import com.example.capstoneproject.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

    @Query("SELECT q FROM Question q WHERE q.isApproved = false")
    List<Question> findAllByIsFalse();

    @Query("SELECT q FROM Question q WHERE q.topic = ?1")
    List<Question> findByTopic(String topic);
}
