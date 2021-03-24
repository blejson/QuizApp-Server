package com.blejson.Server.dto;

import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import lombok.*;

import java.util.function.BiFunction;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateQuestionRequest {
    private String question;
    private String answer0;
    private String answer1;
    private String answer2;
    private String answer3;
    private int goodAnswer;
    public static BiFunction<Question, UpdateQuestionRequest, Question> dtoToEntityUpdater() {
        return (question, request) -> {
            question.setQuestion(request.getQuestion());
            question.setAnswer0(request.getAnswer0());
            question.setAnswer1(request.getAnswer1());
            question.setAnswer2(request.getAnswer2());
            question.setAnswer3(request.getAnswer3());
            question.setGoodAnswer(request.getGoodAnswer());
            return question;
        };
    }

}
