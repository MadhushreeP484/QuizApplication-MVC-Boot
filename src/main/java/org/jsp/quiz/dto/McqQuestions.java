package org.jsp.quiz.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class McqQuestions {
	
	@Id
	@GeneratedValue(generator = "mcq")
	@SequenceGenerator(name = "mcq", initialValue = 501, allocationSize = 1)
	private int id;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private int marks;
	private String subject;

}
