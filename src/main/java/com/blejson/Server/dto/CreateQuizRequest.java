package com.blejson.Server.dto;

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
public class CreateQuizRequest {
    private String id;
    private String author;
    public static Function<CreateQuizRequest, Quiz> dtoToEntityMapper(){
        return request -> Quiz.builder()
                .id(request.getId())
                .author(request.getAuthor())
                .build();
    }
}
