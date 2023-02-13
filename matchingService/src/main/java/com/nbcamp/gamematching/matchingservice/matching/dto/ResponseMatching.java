package com.nbcamp.gamematching.matchingservice.matching.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseMatching {
    @Enumerated(value = EnumType.STRING)
    private MatchingStatusEnum metchingEunm;
    private String playModeEnum;
    private String gameName;
    private String memberNumbers;
    private String discordUrl;

    private List<RequestMatching> members;

    @Builder
    public ResponseMatching(MatchingStatusEnum metchingEunm, String playModeEnum, String gameName,String memberNumbers, String discordUrl, List<RequestMatching> members) {
        this.metchingEunm = metchingEunm;
        this.playModeEnum = playModeEnum;
        this.gameName = gameName;
        this.memberNumbers = memberNumbers;
        this.discordUrl = discordUrl;
        this.members = members;
    }
    @Builder
    public ResponseMatching(MatchingStatusEnum metchingEunm, String playModeEnum, String gameName) {
        this.metchingEunm = metchingEunm;
        this.playModeEnum = playModeEnum;
        this.gameName = gameName;
    }

}
