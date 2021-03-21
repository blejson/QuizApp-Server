package com.blejson.Server.controllers;

import com.blejson.Server.dto.CreateQuizRequest;
import com.blejson.Server.dto.GetQuizzesResponse;
import com.blejson.Server.entity.Quiz;
import com.blejson.Server.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/quiz")
public class QuizController {
    private QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @GetMapping
    public ResponseEntity<GetQuizzesResponse> getQuizzes(){
        List<Quiz> quizzes = quizService.findAll();
        Function<Collection<Quiz>, GetQuizzesResponse> mapper = GetQuizzesResponse.entityToDtoMapper();
        GetQuizzesResponse response = mapper.apply(quizzes);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> createQuiz(@RequestBody CreateQuizRequest request, UriComponentsBuilder builder){
        Quiz quiz = CreateQuizRequest
                .dtoToEntityMapper()
                .apply(request);
        quiz = quizService.create(quiz);
        return ResponseEntity.created(builder.pathSegment("api", "quiz","{id}")
                .buildAndExpand(quiz.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable("id") String id) {
        Optional<Quiz> quiz = quizService.findById(id);
        if (quiz.isPresent()) {
            quizService.delete(quiz.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
