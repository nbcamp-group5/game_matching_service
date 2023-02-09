package com.nbcamp.gamematching.matchingservice.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


// 테스트 용도
@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;
    private String boardImageUrl;
    private String content;

    private Long memberId;

    @Builder
    public Board(String nickname, String boardImageUrl, String content, Long memberId) {
        this.nickname = nickname;
        this.boardImageUrl = boardImageUrl;
        this.content = content;
        this.memberId = memberId;
    }
}