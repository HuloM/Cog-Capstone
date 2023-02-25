package com.example.capstoneproject.controller;

import com.example.capstoneproject.entity.Answer;
import com.example.capstoneproject.entity.dto.AnswerDTO;
import com.example.capstoneproject.entity.dto.AnswerUpdateDTO;
import com.example.capstoneproject.service.FileRenameService;
import com.example.capstoneproject.service.interfaces.AnswerService;
import com.example.capstoneproject.service.interfaces.StorageService;
import com.example.capstoneproject.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private SessionUserUtil sessionUserUtil = new SessionUserUtil();

    @GetMapping("/getAll")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswer();
    }
    @GetMapping("/getAllNotApproved")
    public List<Answer> getAllAnswersFalse() {
        return answerService.getAllAnswerFalse();
    }
    @GetMapping("/getById/{id}")
    public Answer getAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }
    @GetMapping("/getByQuestionId/{id}")
    public Answer getAnswerByQuestionId(@PathVariable int id) {
        return answerService.getAnswerByQuestionId(id);
    }
    @PostMapping("/add")
    public Answer addAnswer(@ModelAttribute AnswerDTO aDTO, @RequestParam("file") MultipartFile file) {
        aDTO.setCreated_by(sessionUserUtil.getSessionUser());

        storageService.store(file);

        Answer answer = new Answer(aDTO);

        answer.setImg_src(fileRenameService.getCurrentFileName());

        return answerService.addAnswer(answer);
    }
    @PutMapping("/update")
    public Answer updateAnswer(@RequestBody AnswerUpdateDTO aDTO) {
        Answer answer = answerService.getAnswerById(aDTO.getId());

        answer.setDatetime(aDTO.getDatetime());



        return answerService.updateAnswer(answer);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteAnswer(@PathVariable int id) {
        return answerService.deleteAnswerById(id);
    }

}
