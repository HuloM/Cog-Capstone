package com.example.capstoneproject.repository;

import com.example.capstoneproject.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
}
