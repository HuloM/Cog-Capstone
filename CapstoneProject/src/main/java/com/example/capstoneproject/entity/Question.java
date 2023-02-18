package com.example.capstoneproject.entity;

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
    private String status;
    private String topic;
    private String title;
    @OneToMany(mappedBy = "question", fetch= FetchType.EAGER)
    @JsonIgnore
    private List<Answer> answers;
    private String qcreated_by;
    private String qapproved_by;
}
