package com.example.capstoneproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerUpdateDTO {
    private int id;
    private String description_question;
    private String datetime;
    private String topic;
}
