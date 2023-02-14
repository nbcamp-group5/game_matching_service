package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateProfileRequest {

    private String nickname;
    private Tier tier;
    private GameType game;
}
