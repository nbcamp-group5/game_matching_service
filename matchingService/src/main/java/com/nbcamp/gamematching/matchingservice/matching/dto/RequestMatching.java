package com.nbcamp.gamematching.matchingservice.matching.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RequestMatching {

    private String gameMode;
    private String gameName;

    private Long memberId;
    private String memberNumbers;
    private String discordId;
    private String discordUrl;
    private String key;

    @Builder
    public RequestMatching(String gameMode, String gameName,Long memberId, String discordId, String memberNumbers, String discordUrl) {
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.memberId = memberId;
        this.discordId = discordId;
        this.memberNumbers = memberNumbers;
        this.discordUrl = discordUrl;
        this.key = this.gameName + this.memberNumbers;
    }

}
