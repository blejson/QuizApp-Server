package com.blejson.Server.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Entity
@Table(name="quiz")
public class Quiz {
    @Id
    private String id;
    private String author;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    private List<Question> questions;
}
