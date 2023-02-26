package com.example.capstoneproject.repository;

import com.example.capstoneproject.entity.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
    @Query("SELECT c FROM Chat c WHERE c.from_user = ?1 GROUP BY c.to_user")
    List<Chat> getChatByRecieverGroupBy(String sender);

    @Query("SELECT c FROM Chat c WHERE c.from_user = ?2 AND c.to_user = ?1")
    List<Chat> getChatsToSenderFromReciever(String sender, String reciever);

    @Query("SELECT c FROM Chat c WHERE c.from_user = ?1 AND c.to_user = ?2")
    List<Chat> getChatsFromSenderToReciever(String sender, String reciever);
}
