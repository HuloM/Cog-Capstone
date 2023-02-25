package com.example.capstoneproject.controller;

import com.example.capstoneproject.entity.Question;
import com.example.capstoneproject.entity.User;
import com.example.capstoneproject.entity.dto.QuestionDTO;
import com.example.capstoneproject.entity.dto.QuestionUpdateDTO;
import com.example.capstoneproject.entity.response.Response;
import com.example.capstoneproject.service.FileRenameService;
import com.example.capstoneproject.service.interfaces.QuestionService;
import com.example.capstoneproject.service.interfaces.StorageService;
import com.example.capstoneproject.service.interfaces.UserService;
import com.example.capstoneproject.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private  StorageService storageService;

    @Autowired
    private FileRenameService fileRenameService;

    private SessionUserUtil sessionUserUtil = new SessionUserUtil();

    @GetMapping("/getAll")
    public Response getAllQuestions() {
        return new Response("successfully got all questions", questionService.getAllQuestions(),200);
    }
    @GetMapping("/getAllNotApproved")
    public Response getAllQuestionsFalse() {
        return new Response("successfully got all unapproved questions", questionService.getAllQuestionsFalse(),200);
    }
    @GetMapping("/getById/{id}")
    public Response getQuestionById(@PathVariable int id) {
        return new Response("successfully got question with id: " + id, questionService.getQuestionById(id),200);
    }
    @GetMapping("/getByTopic/{topic}")
    public Response getQuestionByTopic(@PathVariable String topic) {
        return new Response("successfully got all questions of topic: " + topic, questionService.getQuestionByTopic(topic),200);
    }
    @PostMapping("/add")
    public Response addQuestion(@ModelAttribute QuestionDTO qDTO, @RequestParam("file") MultipartFile file) {
        String creator = sessionUserUtil.getSessionUser();

        qDTO.setCreated_by(creator);

        storageService.store(file);
        Question question = new Question(qDTO);
        question.setImage_src(fileRenameService.getCurrentFileName());

        questionService.addQuestion(question);
        return new Response("successfully added question", question,201);
    }
    @PutMapping("/update")
    public Response updateQuestion(@RequestBody QuestionUpdateDTO qDTO) {
        String creator = sessionUserUtil.getSessionUser();
        User user = userService.getUserByUsername(creator);
        Question question = questionService.getQuestionById(qDTO.getId());

        if(!question.getCreated_by().equals(creator) || !user.getUserType().equals("admin")) {
            return new Response("You are not allowed to update this question", 1,403);
        }

        question.setDescription_question(qDTO.getDescription_question());
        question.setDatetime(qDTO.getDatetime());
        question.setTopic(qDTO.getTopic());

        questionService.updateQuestion(question);
        return new Response("successfully updated question", question,201);
    }
    @DeleteMapping("/delete/{id}")
    public Response deleteQuestion(@PathVariable int id) {
        String creator = sessionUserUtil.getSessionUser();
        User user = userService.getUserByUsername(creator);

        Question question = questionService.getQuestionById(id);

        if(!question.getCreated_by().equals(creator) || !user.getUserType().equals("admin")) {
            return new Response("You are not allowed to delete this question", 1,403);
        }

        //TODO implement delete image
//        Question question = questionService.getQuestionById(id);
//        storageService.delete(question.getImage_src());

        questionService.deleteQuestionById(id);
        return new Response("successfully deleted question with id: " + id, 1,200);
    }

}
