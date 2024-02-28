package com.example.mission3.domain.admin.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DepartmentType {

    CURRICULUM("커리큘럼"), MARKETING("마케팅"), DEVELOPMENT("개발");

    private final String value;
}
