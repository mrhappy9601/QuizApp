package com.telusko.quizeapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telusko.quizeapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	List<Question> findBycategory(String catagory);
	
//	@Query(value="SELECT * FROM question WHERE category = catagory ORDER BY RANDOM() LIMIT numQ",nativeQuery=true)
//	List<Question> findRandomQuestionsBycatagory(String catagory, int numQ);


//	@Query(value = "SELECT * FROM question WHERE category = catagory ORDER BY RANDOM() LIMIT numQ", nativeQuery = true)
//	List<Question> findRandomQuestionsByCategory(String catagory, int numQ);

	
	@Query("SELECT q FROM Question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ")
	List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);


}
