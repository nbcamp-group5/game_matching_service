package com.nbcamp.gamematching.matchingservice.member.entity;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.MannerPoint;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.dto.UpdateProfileRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Column
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
        if (Pattern.matches("\\w{2,8}", nickname)) {
            this.nickname = nickname;
        }
        this.profileImage = profileImage;

        if (Tier.isContains(tier)) {
            this.tier = tier;
        }
        if (GameType.isContains(game)) {
            this.game = game;
        }
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

    public void modifyMannerPoints(MannerPoint mannerPoint) {

        if (mannerPoint == MannerPoint.PLUS) {
            this.mannerPoints += 10;
        } else if (mannerPoint == MannerPoint.MINUS) {
            this.mannerPoints -= 10;
        }
    }
}
