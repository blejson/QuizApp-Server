package com.blejson.Server.services;

import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import com.blejson.Server.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private QuestionRepository repository;

    @Autowired
    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Optional<Question> findById(Long id){ return repository.findById(id); }

    public List<Question> findAll(){ return repository.findAll(); }

    public List<Question> findByQuiz(Quiz quiz){ return repository.findByQuiz(quiz); }

    @Transactional
    public Question create(Question question){ return repository.save(question); }

    @Transactional
    public void deleteById(Long id){ repository.deleteById(id); }

    @Transactional
    public void delete(Question question){ repository.delete(question); }

    @Transactional
    public void update(Question question) { repository.save(question); }
}

