package org.jsp.quiz.repository;

import org.jsp.quiz.dto.McqQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McqQuestionsRepository extends JpaRepository<McqQuestions, Integer>{

}
