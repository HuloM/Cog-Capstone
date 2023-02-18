package com.example.capstoneproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description_answer;
    private String img_src;
    private String status;
    private String datetime;
    @ManyToOne
    private
    Question question;
    private String approved_by;
    private String created_by;
}
