package com.example.capstoneproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private String description_question;
    private String datetime;
    private String topic;
    private String title;
    private String created_by;
}
