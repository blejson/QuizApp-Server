package com.blejson.Server.dto;

import com.blejson.Server.entity.Question;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetQuestionResponse {
    private Long id;
    private String question;
    private String answer0;
    private String answer1;
    private String answer2;
    private String answer3;
    private int goodAnswer;
    public static Function<Question, GetQuestionResponse> entityToDtoMapper(){
        return question -> GetQuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .answer0(question.getAnswer0())
                .answer1(question.getAnswer1())
                .answer2(question.getAnswer2())
                .answer3(question.getAnswer3())
                .goodAnswer(question.getGoodAnswer())
                .build();
    }
}
