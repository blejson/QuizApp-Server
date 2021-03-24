package com.blejson.Server.services;

import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import com.blejson.Server.repositories.QuestionRepository;
import com.blejson.Server.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private QuestionRepository repository;
    private QuizRepository quizRepository;

    @Autowired
    public QuestionService(QuestionRepository repository, QuizRepository quizRepository) {
        this.repository = repository;
        this.quizRepository = quizRepository;
    }

    public Optional<Question> findById(Long id){ return repository.findById(id); }

    public List<Question> findAll(){ return repository.findAll(); }

    public List<Question> findByQuiz(Quiz quiz){ return repository.findByQuiz(quiz); }

    public Optional<Question> findByQuizAndId(String quizID, Long questionID){
        Optional<Quiz> quiz = quizRepository.findById(quizID);
        if(quiz.isPresent()){
            return repository.findByIdAndQuiz(questionID, quiz.get());
        }
        else{
            return Optional.empty();
        }
    }

    @Transactional
    public Question create(Question question){ return repository.save(question); }

    @Transactional
    public void deleteById(Long id){ repository.deleteById(id); }

    @Transactional
    public void delete(Question question){ repository.delete(question); }

    @Transactional
    public void update(Question question) { repository.save(question); }
}

