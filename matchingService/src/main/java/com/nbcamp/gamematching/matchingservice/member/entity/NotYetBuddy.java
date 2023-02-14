package com.nbcamp.gamematching.matchingservice.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 테스트용
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotYetBuddy {

    @Id
    @GeneratedValue
    private Long id;

    private String profileImage;

    private String nickname;

}
