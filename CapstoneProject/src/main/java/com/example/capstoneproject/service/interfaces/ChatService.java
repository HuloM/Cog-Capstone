package com.example.capstoneproject.service.interfaces;

import com.example.capstoneproject.entity.Chat;

import java.util.List;

public interface ChatService {
    Chat addChat(Chat chat);
    List<Chat> getChatByRecieverGroupBy(String sender);
    List<Chat> getChatsToSenderFromReciever(String sender, String reciever);
    List<Chat> getChatsFromSenderToReciever(String sender, String reciever);
}
