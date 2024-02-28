package com.example.mission3.domain.admin.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorityType {

    MANAGER("매니저"), STAFF("스태프");

    private final String value;
}
