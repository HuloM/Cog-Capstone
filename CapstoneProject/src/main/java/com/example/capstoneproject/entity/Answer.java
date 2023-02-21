package com.example.capstoneproject.entity;

import com.example.capstoneproject.entity.dto.AnswerDTO;
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
    private boolean isApproved;
    @ManyToOne
    private
    Question question;
    private String approved_by;
    private String created_by;

    public Answer(AnswerDTO aDTO) {
        this.description_answer = aDTO.getDescription_answer();
        this.img_src = aDTO.getImg_src();
        this.datetime = aDTO.getDatetime();
        this.created_by = aDTO.getCreated_by();
        this.isApproved = false;
        this.approved_by = "";
    }
}
