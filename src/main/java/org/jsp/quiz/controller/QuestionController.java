package org.jsp.quiz.controller;

import org.jsp.quiz.dto.DescriptionQuestions;
import org.jsp.quiz.dto.McqQuestions;
import org.jsp.quiz.dto.Trainer;
import org.jsp.quiz.dto.TrueFalseQuestions;
import org.jsp.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/trainer")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/add-question-mcq")
	public String addMcqQuestion(McqQuestions mcqQuestions, ModelMap map, HttpSession session) {
		Trainer trainer=(Trainer) session.getAttribute("trainer");
		if(trainer==null) {
			map.put("fail", "Invalid Session");
			return "TrainerLogin";
		}else 
			return questionService.addMcqQuestion(mcqQuestions, map, session, trainer);
	}
	
	@PostMapping("/add-question-description")
	public String addDescriptionQuestion(DescriptionQuestions descriptionQuestions, ModelMap map, HttpSession session) {
		Trainer trainer=(Trainer) session.getAttribute("trainer");
		if(trainer==null) {
			map.put("fail", "Invalid Session");
			return "TrainerLogin";
		}else 
			return questionService.addDescriptionQuestion(descriptionQuestions, map, session, trainer);
		
	}
	
	@PostMapping("/add-question-truefalse")
	public String addTruefalseQuestion(TrueFalseQuestions trueFalseQuestions, ModelMap map, HttpSession session) {
		Trainer trainer=(Trainer) session.getAttribute("trainer");
		if(trainer==null) {
			map.put("fail", "Invalid Session");
			return "TrainerLogin";
		}else 
			return questionService.addTrueFalseQuestions(trueFalseQuestions, map, session, trainer);
		
	}
	
	@GetMapping("/questions/{batchCode}")
	public String addTestName(@PathVariable String batchCode, ModelMap map, HttpSession session) {
		return questionService.addTestName(batchCode,map,session);
	}
}
