package com.example.capstoneproject.repository;

import com.example.capstoneproject.entity.Answer;
import com.example.capstoneproject.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    @Query("SELECT a FROM Answer a WHERE a.isApproved = false")
    List<Answer> findAllByIsFalse();

    @Query("SELECT a FROM Answer a WHERE a.question = ?1")
    List<Answer> findByQuestionId(Question quest);
}
