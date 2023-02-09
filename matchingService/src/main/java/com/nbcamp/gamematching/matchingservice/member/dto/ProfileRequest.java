package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import lombok.Getter;

@Getter
public class ProfileRequest {

    private String image;
    private String nickname;
    private Integer score;
    private GameType game;
}
