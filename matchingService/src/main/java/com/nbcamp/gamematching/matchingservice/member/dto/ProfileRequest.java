package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import java.util.Objects;
import lombok.Getter;

@Getter
public class ProfileRequest {

    private final String image;
    private final String nickname;
    private final Tier tier;
    private final GameType game;

    public ProfileRequest(String image, String nickname, Tier tier, GameType game) {
        this.image = Objects.requireNonNullElse(image, "");
        this.nickname = Objects.requireNonNullElse(nickname, "");
        this.tier = tier;
        this.game = game;
    }
}
