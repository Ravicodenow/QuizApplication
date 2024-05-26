package com.Ravicodenow.Quizapplication.services;

import com.Ravicodenow.Quizapplication.dao.QuestionDao;
import com.Ravicodenow.Quizapplication.dao.QuizDao;
import com.Ravicodenow.Quizapplication.model.Question;
import com.Ravicodenow.Quizapplication.model.QuestionWrapper;
import com.Ravicodenow.Quizapplication.model.Quiz;
import com.Ravicodenow.Quizapplication.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        Quiz quiz = new Quiz();
       quiz.setTitle(title);
       List<Question> questions =  questionDao.getQuestionByCategoryNumqAndTitle(category,numQ);
       quiz.setQuestions(questions);
       quizDao.save(quiz);
       return new ResponseEntity<>("Success!", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
        for(Question q: questionsFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUsers.add(qw);
        }
        return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz= quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRight_ans()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
