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

    @GetMapping("/getChatsToSenderFromReciever/{reciever}")
    public Response getChatsToSenderFromReciever(@PathVariable String recieverUsername) {
        String sender = sessionUserUtil.getSessionUser();

        User reciever = userService.getUserByUsername(recieverUsername);
        if (reciever == null) {
            return new Response("reciever does not exist", null, 400);
        }

        return new Response("successfully got chats from " + reciever + " to " + sender,
                chatService.getChatsToSenderFromReciever(sender, reciever.getUsername()), 200);
    }

    @GetMapping("/getChatsFromSenderToReciever/{reciever}")
    public Response getChatsFromSenderToReciever(@PathVariable String recieverUsername) {
        String sender = sessionUserUtil.getSessionUser();

        User reciever = userService.getUserByUsername(recieverUsername);
        if (reciever == null) {
            return new Response("reciever does not exist", null, 400);
        }

        return new Response("successfully got chats from " + sender + " to " + reciever,
                chatService.getChatsFromSenderToReciever(sender, reciever.getUsername()), 200);
    }

    @PostMapping("/addChat")
    public Response addChat(@RequestBody ChatDTO cDTO) {
        String sender = sessionUserUtil.getSessionUser();

        User reciever = userService.getUserByUsername(cDTO.getReciever());
        if (reciever == null) {
            return new Response("reciever does not exist", null, 400);
        }

        Chat chat = new Chat(cDTO);
        chat.setFrom_user(sender);
        chatService.addChat(chat);
        return new Response("successfully added chat", chat, 200);
    }
}
