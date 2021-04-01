package com.blejson.Server.controllers;

import com.blejson.Server.dto.CreateQuestionRequest;
import com.blejson.Server.dto.GetQuestionResponse;
import com.blejson.Server.dto.GetQuestionsResponse;
import com.blejson.Server.dto.UpdateQuestionRequest;
import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import com.blejson.Server.services.QuestionService;
import com.blejson.Server.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/quiz/{quizID}/questions")
public class QuizQuestionController {
    private QuestionService questionService;
    private QuizService quizService;
    @Autowired
    public QuizQuestionController(QuestionService questionService, QuizService quizService) {
        this.questionService = questionService;
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<GetQuestionsResponse> getQuestions(@PathVariable("quizID") String id){
        Optional<Quiz> quiz = quizService.findById(id);
        return quiz.map(value -> ResponseEntity.ok(GetQuestionsResponse.entityToDtoMapper().apply(questionService.findByQuiz(value))))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("{questionID}")
    public ResponseEntity<GetQuestionResponse> getQuestion(@PathVariable("quizID") String quizID, @PathVariable("questionID") Long questionID){
        return questionService.findByQuizAndId(quizID, questionID)
                .map(value -> ResponseEntity.ok(GetQuestionResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Void> createQuestion(@PathVariable("quizID") String quizID, @RequestBody CreateQuestionRequest request, UriComponentsBuilder builder){
        Optional<Quiz> quiz = quizService.findById(quizID);
        if(quiz.isPresent()){
            Question question = CreateQuestionRequest
                    .dtoToEntityMapper(quiz::get)
                    .apply(request);
            question = questionService.create(question);
            return ResponseEntity.created(builder.pathSegment("api","quiz","{quizID}","questions","{id}")
                    .buildAndExpand(quiz.get().getId(), question.getId()).toUri()).build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{questionID}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("quizID") String quizID, @PathVariable("questionID") Long questionID){
        Optional<Question> question = questionService.findByQuizAndId(quizID, questionID);
        if(question.isPresent()){
            questionService.delete(question.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{questionID}")
    public ResponseEntity<Void> updateClient(@PathVariable("quizID") String quizID,
                                             @RequestBody UpdateQuestionRequest request,
                                             @PathVariable("questionID") Long questionID){
        Optional<Question> question = questionService.findByQuizAndId(quizID, questionID);
        if(question.isPresent()){
            UpdateQuestionRequest.dtoToEntityUpdater().apply(question.get(), request);
            questionService.update(question.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
