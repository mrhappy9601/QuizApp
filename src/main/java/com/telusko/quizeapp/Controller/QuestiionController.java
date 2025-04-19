package com.telusko.quizeapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizeapp.model.Question;
import com.telusko.quizeapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestiionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@GetMapping("catagory/{catagory}")
	public ResponseEntity<List<Question>> getQuestionsBycategory(@PathVariable String category) {
		return questionService.getQuestionsBycategory(category);
	}

	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Integer id) {
		return questionService.deleteQuestionById(id);
	}
}
