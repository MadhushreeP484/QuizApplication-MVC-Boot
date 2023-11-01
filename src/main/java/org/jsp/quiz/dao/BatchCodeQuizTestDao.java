package org.jsp.quiz.dao;

import java.util.List;

import org.jsp.quiz.dto.BatchCode;
import org.jsp.quiz.dto.QuizTest;
import org.jsp.quiz.repository.BatchCodeRepository;
import org.jsp.quiz.repository.QuizTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BatchCodeQuizTestDao {
	
	@Autowired
	private BatchCodeRepository batchCodeRepository;
	
	@Autowired
	private QuizTestRepository testRepository;
	
	public void saveBatchCode(BatchCode batchCode) {
		batchCodeRepository.save(batchCode);
	}
	
	public BatchCode fetchById(String id) {
		return batchCodeRepository.findById(id).orElse(null);
	}
	
	public List<BatchCode> fetchAllBatchCodes(){
		return batchCodeRepository.findAll();
	}
	
	public void saveTest(QuizTest quizTest) {
		testRepository.save(quizTest);
	}

}
