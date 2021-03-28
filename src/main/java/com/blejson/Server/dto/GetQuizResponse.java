package com.blejson.Server.dto;

import com.blejson.Server.entity.Question;
import com.blejson.Server.entity.Quiz;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetQuizResponse {
    private String id;
    private String author;
    public static Function<Quiz, GetQuizResponse> entityToDtoMapper(){
        return quiz -> GetQuizResponse.builder()
                .id(quiz.getId())
                .author(quiz.getAuthor())
                .build();
    }
}
