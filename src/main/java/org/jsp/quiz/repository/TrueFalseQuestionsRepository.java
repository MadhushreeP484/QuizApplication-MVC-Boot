package org.jsp.quiz.repository;

import org.jsp.quiz.dto.TrueFalseQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrueFalseQuestionsRepository extends JpaRepository<TrueFalseQuestions, Integer>{

}
