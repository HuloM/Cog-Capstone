package com.example.capstoneproject.entity;

import com.example.capstoneproject.entity.dto.QuestionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description_question;
    private String image_src;
    private String datetime;
    private String topic;
    private String title;
    private boolean isApproved;
    @OneToMany(mappedBy = "question", fetch= FetchType.EAGER)
    @JsonIgnore
    private List<Answer> answers;
    private String created_by;
    private String approved_by;

    public Question(QuestionDTO dto) {
        this.description_question = dto.getDescription_question();
        this.datetime = dto.getDatetime();
        this.topic = dto.getTopic();
        this.title = dto.getTitle();
        this.created_by = dto.getCreated_by();
        this.isApproved = false;
        this.approved_by = "";
    }
}
