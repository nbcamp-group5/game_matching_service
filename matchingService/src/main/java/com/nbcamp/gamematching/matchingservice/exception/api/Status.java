package com.nbcamp.gamematching.matchingservice.exception.api;

import lombok.Getter;

@Getter
public enum Status {

    //중복
    DUPLICATED(400, "중복 오류"),
    DUPLICATED_EMAIL(400, "이미 존재하는 이메일입니다."),
    ALREADY_MEMBER_EXISTS(400, "이미 가입 되어있는 회원입니다."),
    ALREADY_FRIEND(400, "친구목록 존재하는 친구 입니다."),
    ALREADY_APPLIED_FRIEND(400, "이미 친구신청을 했습니다."),

    //접근

    //불일치
    NOT_FOUND(404, "유효성 오류"),
    NOT_FOUND_MEMBER(403, "회원을 찾을 수 없습니다."),
    NOT_FOUND_ROOM(400, "존재하지 않는 방 입니다."),
    NOT_FOUND_DISCORDNAME(400, "존재하지 디스코드계정 입니다."),
    NOT_FOUND_REFRESHTOKEN(400, "토큰이 존재 하지않습니다."),
    APPLY_NOT_APPLIED(404, "친구 신청한 이력이 없습니다."),
    INVALID_EMAIL(401, "올바르지 않는 이메일 형식입니다."),
    INVALID_PASSWORD(401, "올바르지 않는 비밀번호 형식입니다."),
    INVALID_NICKNAME(401, "올바르지 않는 닉네임 형식입니다."),


    //로그인
    Sign(403, "로그인 오류"),
    NOT_FOUND_MEMBER_NOT_MATCHES(403, "아이디 비밀번호를 확인 해주세요."),

    //공통
    AUTHORITY(403, "권한 오류"),
    REFRESHTOKEN_NOT_FOUND(403, "잘못된 토큰 입니다."),
    MEMBER_AUTHORITY(403, "접근 권한이 없습니다."),
    ILLEGAL_ARGUMENT(403, "잘못된 입력 입니다.");

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final int code;
    private final String msg;
}
