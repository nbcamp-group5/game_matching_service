package com.nbcamp.gamematching.matchingservice.matching.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RequestMatching {

    private String discordId;
    private String discordNum;
    private String dicordName;
    private String memberNumbers;
    private String gameMode;
    private String gameName;
    private Long memberId;
    private String key;


    @Builder
    public RequestMatching(RequestMatching requestMatching,Long memberId) {
        this.gameMode = requestMatching.getGameMode();
        this.gameName = requestMatching.getGameName();
        this.memberNumbers = requestMatching.getMemberNumbers();
        this.discordId = requestMatching.getDiscordId();
        this.discordNum = requestMatching.getDiscordNum();
        this.memberId = memberId;
        this.dicordName = this.discordId+this.discordNum;
        this.key = this.gameName + this.memberNumbers;
    }

    public RequestMatching(String discordId, String discordNum, String dicordName, String memberNumbers, String gameMode, String gameName,String key) {
        this.discordId = discordId;
        this.discordNum = discordNum;
        this.dicordName = dicordName;
        this.memberNumbers = memberNumbers;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.key = key;
    }
}
