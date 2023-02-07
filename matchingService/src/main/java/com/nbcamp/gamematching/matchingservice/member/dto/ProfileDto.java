package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import lombok.Getter;

@Getter
public class ProfileDto {

    private String nickname;
    private String Image;
    private GameType game;
    private Tier tier;
    private int mannerPoints;

    public ProfileDto(Profile profile) {
        this.nickname = profile.getNickname();
        this.Image = profile.getProfileImage();
        this.game = profile.getGame();
        this.tier = profile.getTier();
        this.mannerPoints = profile.getMannerPoints();
    }
}
