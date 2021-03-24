package com.blejson.Server.dto;

import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateQuestionRequest {
    private Quiz quiz;
    private String question;
    private String answer0;
    private String answer1;
    private String answer2;
    private String answer3;
    private int goodAnswer;
    public static Function<CreateQuestionRequest, Question> dtoToEntityMapper(
            Supplier<Quiz> quizSupplier) {
        return request -> Question.builder()
                .quiz(quizSupplier.get())
                .question(request.getQuestion())
                .answer0(request.getAnswer0())
                .answer1(request.getAnswer1())
                .answer2(request.getAnswer2())
                .answer3(request.getAnswer3())
                .goodAnswer(request.getGoodAnswer())
                .build();
    }
}
