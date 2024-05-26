package com.Ravicodenow.Quizapplication.dao;

import com.Ravicodenow.Quizapplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question> getQuestionByCategoryNumqAndTitle(String category, Integer numQ);
}
