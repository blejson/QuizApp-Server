package com.blejson.Server.repositories;

import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findById(Long id);
    List<Question> findByQuiz(Quiz quiz);
    List<Question> findAll();
    void delete(Question question);
    Optional<Question> findByIdAndQuiz(Long questionID, Quiz quiz);
}
