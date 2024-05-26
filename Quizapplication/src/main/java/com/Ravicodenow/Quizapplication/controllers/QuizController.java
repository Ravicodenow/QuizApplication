package com.Ravicodenow.Quizapplication.controllers;

import com.Ravicodenow.Quizapplication.model.QuestionWrapper;
import com.Ravicodenow.Quizapplication.model.Quiz;
import com.Ravicodenow.Quizapplication.model.Response;
import com.Ravicodenow.Quizapplication.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz-api")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> getQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("quiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public  ResponseEntity<Integer> submitAns(@PathVariable Integer id, @RequestBody List<Response> responses){
               return quizService.calculateResult(id,responses);
    }

}
