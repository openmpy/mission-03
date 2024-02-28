package com.example.mission3.domain.admin.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorityType {

    MANAGER("매니저", Authority.MANAGER), STAFF("스태프", Authority.STAFF);

    private final String value;
    private final String authority;

    public static class Authority {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}
