package com.nbcamp.gamematching.matchingservice.matching.domain;

import lombok.Getter;

@Getter
public class MemberLog {
    private Long memberId;
    private String nickname;
    public MemberLog(Long memberId, String nickname) {
        this.memberId = memberId;
        this.nickname = nickname;
    }
}
