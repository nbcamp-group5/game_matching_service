package com.nbcamp.gamematching.matchingservice.matching.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RequestMatching {

    private String discordId;
    private String discordNum;
    private String discordName;
    private String memberNumbers;
    private String gameMode;
    private String gameName;
    private String memberEmail;
    private String key;

    @Builder
    public RequestMatching(RequestMatching requestMatching,String memberEmail) {
        this.gameMode = requestMatching.getGameMode();
        this.gameName = requestMatching.getGameName();
        this.memberNumbers = requestMatching.getMemberNumbers();
        this.discordId = requestMatching.getDiscordId();
        this.discordNum = requestMatching.getDiscordNum();
        this.memberEmail = memberEmail;
        this.discordName = this.discordId+"#"+this.discordNum;
        this.key = this.gameName + this.memberNumbers;
    }

    public RequestMatching(String discordId, String discordNum, String discordName, String memberNumbers, String gameMode, String gameName,String key) {
        this.discordId = discordId;
        this.discordNum = discordNum;
        this.discordName = discordName;
        this.memberNumbers = memberNumbers;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.key = key;
    }
}
