package com.blejson.Server.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name="quiz")
public class Quiz {
    @Id
    private String id;
    @OneToMany(mappedBy = "question")
    private List<Question> clients;
}
