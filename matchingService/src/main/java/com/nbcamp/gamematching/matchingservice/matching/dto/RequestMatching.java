package com.nbcamp.gamematching.matchingservice.matching.dto;

import com.nbcamp.gamematching.matchingservice.matching.entity.GameType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RequestMatching {

    private String gameMode;
    private String gamename;

    private Long memberId;
    private String memberNumbers;
    private String discordId;
    private String discordUrl;
    private String key;

    @Builder
    public RequestMatching(String gameMode, String gamename,Long memberId, String discordId, String memberNumbers, String discordUrl) {
        this.gameMode = gameMode;
        this.gamename = gamename;
        this.memberId = memberId;
        this.discordId = discordId;
        this.memberNumbers = memberNumbers;
        this.discordUrl = discordUrl;
        this.key = this.gamename + this.memberNumbers;
    }

}
