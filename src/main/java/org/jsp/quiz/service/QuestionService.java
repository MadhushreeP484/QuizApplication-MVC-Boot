package org.jsp.quiz.service;
import java.util.List;

import org.jsp.quiz.dao.BatchCodeQuizTestDao;
import org.jsp.quiz.dao.QuestionsDao;
import org.jsp.quiz.dto.DescriptionQuestions;
import org.jsp.quiz.dto.McqQuestions;
import org.jsp.quiz.dto.Trainer;
import org.jsp.quiz.dto.TrueFalseQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import jakarta.servlet.http.HttpSession;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionsDao questionsDao;
	
	@Autowired
	private BatchCodeQuizTestDao batchCodeDao;
	
	public String addMcqQuestion(McqQuestions question, ModelMap map, HttpSession session, Trainer trainer) {
		question.setSubject(trainer.getSubject());
		questionsDao.saveMcqQuestion(question);
		map.put("pass", "Question Added");
		map.put("batchCode", batchCodeDao.fetchAllBatchCodes());
		return "TrainerHome";
	}

	public String addDescriptionQuestion(DescriptionQuestions descriptionQuestions, ModelMap map, HttpSession session,
			Trainer trainer) {
		descriptionQuestions.setSubject(trainer.getSubject());
		questionsDao.saveDescriptionQuestion(descriptionQuestions);
		map.put("pass", "Question Added");
		map.put("batchCode", batchCodeDao.fetchAllBatchCodes());
		return "TrainerHome";
	}

	public String addTrueFalseQuestions(TrueFalseQuestions trueFalseQuestions, ModelMap map, HttpSession session,
			Trainer trainer) {
		trueFalseQuestions.setSubject(trainer.getSubject());
		questionsDao.saveTrueFalseQuestion(trueFalseQuestions);
		map.put("pass", "Question Added");
		map.put("batchCode", batchCodeDao.fetchAllBatchCodes());
		return "TrainerHome";
	}

	public String addTestName(String batchCode, ModelMap map, HttpSession session) {
		Trainer trainer=(Trainer) session.getAttribute("trainer");
		if(trainer==null) {
			map.put("fail", "Invalid Session");
			return "TrainerLogin";
		}else {
			map.put("batchCode", batchCode);
			map.put("mcqQ", questionsDao.fetchAllMCQs());
			map.put("descQ", questionsDao.fetchAllDescriptionQues());
			map.put("trueFalseQ", questionsDao.fetchAllTrueFalseQues());
			return "SelectQuestions";
		}
	}

}
