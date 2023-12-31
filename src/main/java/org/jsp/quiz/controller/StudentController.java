package org.jsp.quiz.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsp.quiz.dto.Student;
import org.jsp.quiz.helper.LoginHelper;
import org.jsp.quiz.service.StudentService;
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
@RequestMapping("/student")
public class StudentController {

	@Autowired
	Student student;

	@Autowired
	StudentService studentService;

	@GetMapping("/login")
	public String loadLogin() {
		return "StudentLogin";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("student", student);
		return "StudentSignup";
	}

	@PostMapping("/signup")
	public String signup(@Valid Student student, BindingResult result, @RequestParam MultipartFile pic, ModelMap map)
			throws IOException, MessagingException {
		if (result.hasErrors()) {
			return "StudentSignup";
		} else {
			return studentService.signup(student, pic, map);
		}
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam int id, @RequestParam int otp, ModelMap map) {
		return studentService.verifyOtp(id, otp, map);
	}

	@GetMapping("/resend-otp/{id}")
	public String resendOtp(@PathVariable int id, ModelMap map) throws UnsupportedEncodingException, MessagingException {
		return studentService.resendOtp(id, map);
	}
	
	@PostMapping("/login")
	public String login(LoginHelper helper,ModelMap map,HttpSession session)
	{
		return studentService.login(helper,map,session);
	}
	
	@GetMapping("/forgot-password")
	public String forgotPassword(ModelMap map) {
		return studentService.forgotPassword(map);
	}
	
	@PostMapping("/email-verify")
	public String EmailVerification(@RequestParam String email, ModelMap map) throws UnsupportedEncodingException, MessagingException {
		return studentService.emailVerification(email, map);
	}
	
	@PostMapping("/change-password/{email}")
	public String changePassword(@PathVariable String email,@RequestParam int otp, @RequestParam String password, ModelMap map) {
		return studentService.changePassword(email,otp,password,map);
	}
}
