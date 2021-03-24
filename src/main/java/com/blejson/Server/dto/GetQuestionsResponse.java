package com.blejson.Server.dto;

import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetQuestionsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Question{
        private Long id;
        private String question;
        private String answer0;
        private String answer1;
        private String answer2;
        private String answer3;
        private int goodAnswer;
    }
    @Singular
    private List<Question> questions;
    public static Function<Collection<com.blejson.Server.entity.Question>, GetQuestionsResponse> entityToDtoMapper(){
        return questions -> {
            GetQuestionsResponseBuilder response = GetQuestionsResponse.builder();
            questions.stream()
                    .map(question -> Question.builder()
                            .id(question.getId())
                            .question(question.getQuestion())
                            .answer0(question.getAnswer0())
                            .answer1(question.getAnswer1())
                            .answer2(question.getAnswer2())
                            .answer3(question.getAnswer3())
                            .goodAnswer(question.getGoodAnswer())
                            .build())
                    .forEach(response::question);
            return response.build();
        };
    }
}
