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
    private String playMode;
    private String gameName;
    private String memberNumbers;
    private String discordUrl;
    private List<RequestMatching> members;

    @Builder
    public ResponseMatching(MatchingStatusEnum metchingEunm, String playMode, String gameName, String memberNumbers, String discordUrl, List<RequestMatching> members) {
        this.metchingEunm = metchingEunm;
        this.playMode = playMode;
        this.gameName = gameName;
        this.memberNumbers = memberNumbers;
        this.discordUrl = discordUrl;
        this.members = members;
    }
    @Builder
    public ResponseMatching(MatchingStatusEnum metchingEunm, String playMode, String gameName) {
        this.metchingEunm = metchingEunm;
        this.playMode = playMode;
        this.gameName = gameName;
    }

}
