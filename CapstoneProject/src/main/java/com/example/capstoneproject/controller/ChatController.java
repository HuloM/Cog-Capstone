package com.example.capstoneproject.controller;

import com.example.capstoneproject.entity.Chat;
import com.example.capstoneproject.entity.User;
import com.example.capstoneproject.entity.dto.ChatDTO;
import com.example.capstoneproject.entity.response.Response;
import com.example.capstoneproject.service.interfaces.ChatService;
import com.example.capstoneproject.service.interfaces.UserService;
import com.example.capstoneproject.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    private SessionUserUtil sessionUserUtil = new SessionUserUtil();

    @GetMapping("/getChats")
    public Response getChats() {
        String sender = sessionUserUtil.getSessionUser();

        return new Response("successfully got unique chats",
                chatService.getChatByRecieverGroupBy(sender), 200);
    }

    @GetMapping("/getChatsToSenderFromReceiver/{receiverUsername}")
    public Response getChatsToSenderFromReceiver(@PathVariable String receiverUsername) {
        String sender = sessionUserUtil.getSessionUser();

        User reciever = userService.getUserByUsername(receiverUsername);
        if (reciever == null) {
            return new Response("reciever does not exist", null, 400);
        }

        return new Response("successfully got chats from " + reciever.getUsername() + " to " + sender,
                chatService.getChatsToSenderFromReciever(sender, reciever.getUsername()), 200);
    }

    @GetMapping("/getChatsFromSenderToReceiver/{receiverUsername}")
    public Response getChatsFromSenderToReceiver(@PathVariable String receiverUsername) {
        String sender = sessionUserUtil.getSessionUser();

        User reciever = userService.getUserByUsername(receiverUsername);
        if (reciever == null) {
            return new Response("receiver does not exist", null, 400);
        }

        return new Response("successfully got chats from " + sender + " to " + reciever.getUsername(),
                chatService.getChatsFromSenderToReciever(sender, reciever.getUsername()), 200);
    }

    @PostMapping("/add")
    public Response addChat(@RequestBody ChatDTO cDTO) {
        String sender = sessionUserUtil.getSessionUser();
        System.out.println("cDTO: " + cDTO);
        System.out.println("sender: " + sender);
        System.out.println("receiver: " + cDTO.getReceiver());
        User reciever = userService.getUserByUsername(cDTO.getReceiver());
        System.out.println(reciever);
        if (reciever == null) {
            return new Response("receiver does not exist", null, 400);
        }

        Chat chat = new Chat(cDTO);
        chat.setFrom_user(sender);
        chatService.addChat(chat);
        return new Response("successfully added chat", chat, 200);
    }
}
