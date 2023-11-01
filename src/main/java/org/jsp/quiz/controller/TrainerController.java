package org.jsp.quiz.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsp.quiz.dto.BatchCode;
import org.jsp.quiz.dto.QuizTest;
import org.jsp.quiz.dto.Trainer;
import org.jsp.quiz.helper.LoginHelper;
import org.jsp.quiz.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

	@Autowired
	Trainer trainer;

	@Autowired
	TrainerService trainerService;

	@GetMapping("/login")
	public String loadLogin() {
		return "TrainerLogin";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("trainer", trainer);
		return "TrainerSignup";
	}

	@PostMapping("/signup")
	public String signup(@Valid Trainer trainer, BindingResult result, @RequestParam MultipartFile pic, ModelMap map)
			throws IOException, MessagingException {
		if (result.hasErrors())
			return "TrainerSignup";
		else
			return trainerService.signup(trainer, pic, map);
	}

	@GetMapping("/verify-link/{id}/{token}")
	public String verifyLink(@PathVariable int id, @PathVariable String token, ModelMap map) {
		return trainerService.verifylink(id, token, map);
	}
	
	@PostMapping("/login")
	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		return trainerService.login(helper, map,session);
	}
	
	@GetMapping("/forgot-password")
	public String forgotPassword(ModelMap map) {
		return trainerService.forgotPassword(map);
	}
	
	@PostMapping("/email-verify")
	public String EmailVerification(@RequestParam String email, ModelMap map) throws UnsupportedEncodingException, MessagingException {
		return trainerService.emailVerification(email, map);
	}
	
	@GetMapping("/enter-password/{email}")
	public String changePassword(@PathVariable String email,ModelMap map) {
		return trainerService.enterPassword(email,map);
	}
	
	@PostMapping("/change-password/{email}")
	public String changePassword(@PathVariable String email,@RequestParam String password, ModelMap map) {
		return trainerService.changePassword(email,password,map);
	}
	
	@GetMapping("/create-batchcode")
	public String createBatchCode(HttpSession session, ModelMap map) {
		return trainerService.createBatchCode(session, map);
	}
	
	@PostMapping("/create-batchcode")
	public String createBatchCode(BatchCode batchCode, HttpSession session, ModelMap map) {
		return trainerService.createBatchCode(batchCode,session, map);
	}
	
	@PostMapping("/create-test")
	public String createTest(QuizTest quizTest,String batchCode, ModelMap map, HttpSession session) {
		return trainerService.createTest(quizTest,batchCode,map,session);
	}
}
