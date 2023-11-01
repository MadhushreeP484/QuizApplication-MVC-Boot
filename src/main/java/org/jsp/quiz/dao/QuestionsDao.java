package org.jsp.quiz.dao;

import java.util.List;

import org.jsp.quiz.dto.DescriptionQuestions;
import org.jsp.quiz.dto.McqQuestions;
import org.jsp.quiz.dto.TrueFalseQuestions;
import org.jsp.quiz.repository.DescriptionQuestionsRepository;
import org.jsp.quiz.repository.McqQuestionsRepository;
import org.jsp.quiz.repository.TrueFalseQuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionsDao {
	
	@Autowired
	private McqQuestionsRepository mcqQuestionsRepository;
	
	@Autowired
	private DescriptionQuestionsRepository descriptionQuestionsRepository;
	
	@Autowired
	private TrueFalseQuestionsRepository trueFalseQuestionsRepository;
	
	public void saveMcqQuestion(McqQuestions mcqQuestions) {
		mcqQuestionsRepository.save(mcqQuestions);
	}
	
	public List<McqQuestions> fetchAllMCQs(){
		return mcqQuestionsRepository.findAll();
	}
	
	public void saveDescriptionQuestion(DescriptionQuestions descriptionQuestions) {
		descriptionQuestionsRepository.save(descriptionQuestions);
	}
	
	public List<DescriptionQuestions> fetchAllDescriptionQues(){
		return descriptionQuestionsRepository.findAll();
	}
	
	public void saveTrueFalseQuestion(TrueFalseQuestions questions) {
		trueFalseQuestionsRepository.save(questions);
	}
	
	public List<TrueFalseQuestions> fetchAllTrueFalseQues(){
		return trueFalseQuestionsRepository.findAll();
	}

}
