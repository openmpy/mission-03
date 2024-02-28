package com.example.mission3.domain.lecture.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryType {

    SPRING("스프링"), REACT("리액트"), NODE("노드");

    private final String value;
}
