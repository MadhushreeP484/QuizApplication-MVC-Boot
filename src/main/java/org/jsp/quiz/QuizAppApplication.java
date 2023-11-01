package org.jsp.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
//@CrossOrigin- to change the post number to react
public class QuizAppApplication {
	// By Default will be present we have to run this to start server
	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);
	}

}
	