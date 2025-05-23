package com.telusko.quizeapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizeapp.Dao.QuestionDao;
import com.telusko.quizeapp.model.Question;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsBycategory(String category) {
		try {
		return new ResponseEntity<>(questionDao.findBycategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}

	public ResponseEntity<String>addQuestion(Question question) {
		try {
		 questionDao.save(question);
		}catch(Exception e) {
			e.printStackTrace();
		}
		 return new ResponseEntity<>("successfully added",HttpStatus.CREATED);
	}

	public ResponseEntity<?> deleteQuestionById(Integer id) {
		if(questionDao.existsById(id)) {
		questionDao.deleteById(id);
		return new ResponseEntity<>("deleted...." , HttpStatus.ACCEPTED);
		}else {
		return new ResponseEntity<>("Id is not found" , HttpStatus.NOT_FOUND);}
	}
	

	
}
