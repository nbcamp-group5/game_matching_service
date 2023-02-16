package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UpdateProfileRequest {

    private String nickname;
    private Tier tier;
    private GameType game;

    public UpdateProfileRequest(String nickname, Tier tier, GameType game) {
        this.nickname = nickname;
        this.tier = tier;
        this.game = game;
    }
}
