package com.Ravicodenow.Quizapplication.dao;

import com.Ravicodenow.Quizapplication.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
