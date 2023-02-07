package com.nbcamp.gamematching.matchingservice.member.entity;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private GameType game;

    private Integer mannerPoints;

    @Builder
    public Profile(String nickname, String profileImage, Tier tier, GameType game,
            Integer mannerPoints) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.tier = tier;
        this.game = game;
        this.mannerPoints = mannerPoints;
    }
}
