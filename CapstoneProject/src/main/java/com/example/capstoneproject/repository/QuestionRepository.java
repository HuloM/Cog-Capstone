package com.example.capstoneproject.repository;

import com.example.capstoneproject.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
