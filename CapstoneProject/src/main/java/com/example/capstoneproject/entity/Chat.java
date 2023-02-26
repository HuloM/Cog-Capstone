package com.example.capstoneproject.entity;

import com.example.capstoneproject.entity.dto.ChatDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String from_user;
    private String to_user;
    private String message;
    private String datetime;

    public Chat(ChatDTO dto) {
        this.to_user = dto.getReciever();
        this.message = dto.getMessage();
        this.datetime = dto.getDatetime();
    }
}
