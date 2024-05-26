package com.Ravicodenow.Quizapplication.controllers;

import com.Ravicodenow.Quizapplication.model.Question;
import com.Ravicodenow.Quizapplication.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class use for question controller
 */
@RestController
@RequestMapping("question-api")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("questions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestion();
    }

    @GetMapping("questions/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
       return questionService.getAllQuestionByCategory(category);
    }

    @PostMapping("questions")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
       return  questionService.addQuestion(question);
    }

}
