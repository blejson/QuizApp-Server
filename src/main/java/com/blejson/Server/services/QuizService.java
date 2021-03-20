package com.blejson.Server.services;

import com.blejson.Server.entity.Quiz;
import com.blejson.Server.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private QuizRepository repository;
    @Autowired
    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }
    public Optional<Quiz> findById(String id){ return repository.findById(id); }

    public List<Quiz> findAll(){ return repository.findAll(); }

    @Transactional
    public Quiz create(Quiz quiz){ return repository.save(quiz);}

    @Transactional
    public void deleteById(String id){ repository.deleteById(id);}

    @Transactional
    public void delete(Quiz quiz){ repository.delete(quiz);}

    @Transactional
    public void update(Quiz quiz) {repository.save(quiz);}
}
