package org.jsp.quiz.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class BatchCode {
	
	@Id
	private String batchCode;
	private LocalDate batchCreatedDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<QuizTest> quizTest;

}
