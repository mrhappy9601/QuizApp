package com.telusko.quizeapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.quizeapp.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>{

}
