package com.blejson.Server;

import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import com.blejson.Server.services.QuestionService;
import com.blejson.Server.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Initializer {
    private QuestionService questionService;
    private QuizService quizService;
    @Autowired
    public Initializer(QuestionService questionService, QuizService quizService) {
        this.questionService = questionService;
        this.quizService = quizService;
    }
    @PostConstruct
    private synchronized void init(){
        Quiz quiz = Quiz.builder()
                .id("TEST")
                .build();
        quizService.create(quiz);
        Question question0 = Question.builder()
                .quiz(quiz)
                .question("Ile mam lat?")
                .answer0("18")
                .answer1("20")
                .answer2("22")
                .answer3("24")
                .goodAnswer(2)
                .build();
        Question question1 = Question.builder()
                .quiz(quiz)
                .question("Co trenowałem?")
                .answer0("Skok o tyczce")
                .answer1("Skok w dal")
                .answer2("Rzut oszczepem")
                .answer3("100 m.")
                .goodAnswer(0)
                .build();
        Question question2 = Question.builder()
                .quiz(quiz)
                .question("Jak się nazywam?")
                .answer0("Michał")
                .answer1("Jan")
                .answer2("Oskar")
                .answer3("Jacek")
                .goodAnswer(3)
                .build();
        questionService.create(question0);
        questionService.create(question1);
        questionService.create(question2);
    }
}
