package org.jsp.quiz.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class DescriptionQuestions {

	@Id
	@GeneratedValue(generator = "description")
	@SequenceGenerator(name = "description", initialValue = 1501, allocationSize = 1)
	private int id;
	private String question;
	private String answer;
	private int marks;
	private String subject;
}
