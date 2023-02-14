package com.nbcamp.gamematching.matchingservice.member.domain;

import java.util.HashMap;
import java.util.Map;

public enum MemberRoleEnum {
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private static final Map<String, MemberRoleEnum> stringToEnum = new HashMap<>();

    static {
        for (MemberRoleEnum memberRoleEnum : values()) {
            stringToEnum.put(memberRoleEnum.name(), memberRoleEnum);
        }
    }

    public static boolean isContains(MemberRoleEnum role) {
        return stringToEnum.containsValue(role);
    }

    private final String authority;

    MemberRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {

        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
