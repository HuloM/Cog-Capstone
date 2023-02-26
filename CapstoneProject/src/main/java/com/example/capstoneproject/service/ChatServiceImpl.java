package com.example.capstoneproject.service;

import com.example.capstoneproject.entity.Chat;
import com.example.capstoneproject.repository.ChatRepository;
import com.example.capstoneproject.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository repository;

    @Override
    public Chat addChat(Chat chat) {
        return repository.save(chat);
    }

    @Override
    public List<Chat> getChatByRecieverGroupBy(String sender) {
        return repository.getChatByRecieverGroupBy(sender);
    }

    @Override
    public List<Chat> getChatsToSenderFromReciever(String sender, String reciever) {
        return repository.getChatsToSenderFromReciever(sender, reciever);
    }

    @Override
    public List<Chat> getChatsFromSenderToReciever(String sender, String reciever) {
        return repository.getChatsFromSenderToReciever(sender, reciever);
    }
}
