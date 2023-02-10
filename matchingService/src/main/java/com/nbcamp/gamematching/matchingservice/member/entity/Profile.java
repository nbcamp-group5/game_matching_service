package com.nbcamp.gamematching.matchingservice.member.entity;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.dto.UpdateProfileRequest;
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

    @Column
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private GameType game;

    private Integer mannerPoints = 0;

    @Builder
    public Profile(String nickname, String profileImage, Tier tier, GameType game) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.tier = tier;
        this.game = game;
    }

    public void changeProfile(UpdateProfileRequest profileRequest, String imageDir) {
        if (!imageDir.isEmpty()) {
            this.profileImage = imageDir;
        }
        if (!profileRequest.getNickname().isEmpty()) {
            this.nickname = profileRequest.getNickname();
        }
        if (profileRequest.getGame() != null) {
            this.game = profileRequest.getGame();
        }
        if (profileRequest.getTier() != null) {
            this.tier = profileRequest.getTier();
        }
    }
}
