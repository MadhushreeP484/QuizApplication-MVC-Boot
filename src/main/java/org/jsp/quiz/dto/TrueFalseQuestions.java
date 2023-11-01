package org.jsp.quiz.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class TrueFalseQuestions {
	@Id
	@GeneratedValue(generator = "truefalse")
	@SequenceGenerator(name = "truefalse", initialValue = 1001, allocationSize = 1)
	private int id;
	private String question;
	private String answer;
	private int marks;
	private String subject;

}
