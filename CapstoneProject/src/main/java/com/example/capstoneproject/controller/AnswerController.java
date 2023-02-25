package com.example.capstoneproject.controller;

import com.example.capstoneproject.entity.Answer;
import com.example.capstoneproject.entity.Question;
import com.example.capstoneproject.entity.dto.AnswerDTO;
import com.example.capstoneproject.entity.dto.AnswerUpdateDTO;
import com.example.capstoneproject.entity.response.Response;
import com.example.capstoneproject.service.FileRenameService;
import com.example.capstoneproject.service.interfaces.AnswerService;
import com.example.capstoneproject.service.interfaces.QuestionService;
import com.example.capstoneproject.service.interfaces.StorageService;
import com.example.capstoneproject.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private FileRenameService fileRenameService;
    @Autowired
    private QuestionService questionService;
    private SessionUserUtil sessionUserUtil = new SessionUserUtil();

    @GetMapping("/getAll")
    public Response getAllAnswers() {
        return new Response("successfully got all answers", answerService.getAllAnswer(),200);
    }
    @GetMapping("/getAllNotApproved")
    public Response getAllAnswersFalse() {
        return new Response("successfully got all unapproved answers",
                answerService.getAllAnswerFalse(),200);
    }
    @GetMapping("/getById/{id}")
    public Response getAnswerById(@PathVariable int id) {
        return new Response("successfully got answer with id: " + id, answerService.getAnswerById(id),200);
    }
    @GetMapping("/getByQuestionId/{id}")
    public Response getAnswerByQuestionId(@PathVariable int id) {
        Question question = questionService.getQuestionById(id);
        List<Answer> answers = answerService.getAnswerByQuestionId(question);
        if(answers.size() == 0)
            return new Response("no answer found with question id: " + id, null,404);
        else
            return new Response("successfully got answer with question id: " + id, answers,200);
    }
    @PostMapping("/add")
    public Response addAnswer(@ModelAttribute AnswerDTO aDTO, @RequestParam("file") MultipartFile file) {

        aDTO.setCreated_by(sessionUserUtil.getSessionUser());

        storageService.store(file);
        Question question = questionService.getQuestionById(aDTO.getQuestion_id());
        Answer answer = new Answer(aDTO);
        answer.setQuestion(question);
        answer.setImg_src(fileRenameService.getCurrentFileName());
        answerService.addAnswer(answer);
        return new Response("successfully added answer", answer,200);
    }
    @PutMapping("/update")
    public Response updateAnswer(@RequestBody AnswerUpdateDTO aDTO) {
        Answer answer = answerService.getAnswerById(aDTO.getId());

        answer.setDatetime(aDTO.getDatetime());

        answerService.updateAnswer(answer);
        return new Response("successfully updated answer", answer,200);
    }
    @DeleteMapping("/delete/{id}")
    public Response deleteAnswer(@PathVariable int id) {
        answerService.deleteAnswerById(id);
        return new Response("successfully deleted answer with id: " + id, null,200);
    }
}
