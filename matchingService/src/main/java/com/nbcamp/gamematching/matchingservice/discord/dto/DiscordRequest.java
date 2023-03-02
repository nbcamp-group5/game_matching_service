package com.nbcamp.gamematching.matchingservice.discord.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiscordRequest {

    private String discordId;
    private String discordNum;
    public DiscordRequest(String discordId, String discordNum) {
        this.discordId = discordId;
        this.discordNum = discordNum;
    }
}
