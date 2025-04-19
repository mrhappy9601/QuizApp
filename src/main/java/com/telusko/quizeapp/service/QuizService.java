package com.telusko.quizeapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizeapp.Dao.QuestionDao;
import com.telusko.quizeapp.Dao.QuizDao;
import com.telusko.quizeapp.model.Question;
import com.telusko.quizeapp.model.QuestionWrapper;
import com.telusko.quizeapp.model.Quiz;
import com.telusko.quizeapp.model.Responce;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String catagory, int numQ, String title) {
		
		List<Question>questions = questionDao.findRandomQuestionsByCategory(catagory, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizDao.save(quiz);
		
		return new ResponseEntity<>("successfully created ",HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question>questionFromDB = quiz.get().getQuestions();
		List<QuestionWrapper>questionsFouUser = new ArrayList<>();
		for(Question q: questionFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuesttitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsFouUser.add(qw);
		}
		
		
		return new ResponseEntity<>(questionsFouUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Responce> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question>questions = quiz.getQuestions();
		
		int right = 0;
		int i =0;
		for(Responce response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightans()))
				right++;
			i++;
		}
		
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

	


}
