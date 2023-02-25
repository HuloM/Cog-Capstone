package com.example.capstoneproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private String description_answer;
    private String datetime;
    private String created_by;
    private int question_id;
}
