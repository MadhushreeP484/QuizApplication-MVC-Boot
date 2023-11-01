package org.jsp.quiz.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class QuizTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String testName;
	private int duration;
	private LocalDateTime startTime;
	private int marks;
	private boolean status;
	private boolean completed;
	
	private String batchCode;
	
	@ManyToMany
	private List<McqQuestions> mcqQuestions;
	
	@ManyToMany
	private List<DescriptionQuestions> descriptionQuestions;
	
	@ManyToMany
	private List<TrueFalseQuestions> trueFalseQuestions;
}
