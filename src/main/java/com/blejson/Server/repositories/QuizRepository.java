package com.blejson.Server.repositories;

import com.blejson.Server.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {
    Optional<Quiz> findById(String id);
    List<Quiz> findAll();
    void deleteById(String id);
    void delete(Quiz quiz);

}
