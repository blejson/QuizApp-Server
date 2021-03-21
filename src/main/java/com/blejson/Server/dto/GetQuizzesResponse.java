package com.blejson.Server.dto;

import com.blejson.Server.entity.Quiz;
import lombok.*;

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
public class GetQuizzesResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Quiz{
        private String id;
        private String author;
    }
    @Singular
    private List<Quiz> quizzes;

    public static Function<Collection<com.blejson.Server.entity.Quiz>, GetQuizzesResponse> entityToDtoMapper(){
        return quizzes -> {
            GetQuizzesResponseBuilder response = GetQuizzesResponse.builder();
            quizzes.stream()
                    .map(quiz -> Quiz.builder()
                            .id(quiz.getId())
                            .author(quiz.getAuthor())
                            .build())
                    .forEach(response::quiz);
            return response.build();
        };
    }
}
