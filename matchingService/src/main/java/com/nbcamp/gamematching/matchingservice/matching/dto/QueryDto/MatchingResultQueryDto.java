package com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MatchingResultQueryDto {

    private String key;
    private String email;
    private String url;
    private String gameMode;
    private String gameInfo;
    private List<Long> membersId;

    @QueryProjection
    public MatchingResultQueryDto(String email, String url,
                                  String gameMode, String gameInfo, List<Long> memberNumbers,String key) {
        this.gameMode = gameMode;
        this.gameInfo = gameInfo;
        this.membersId = memberNumbers;
        this.email = email;
        this.url = url;
        this.key = key;
    }
}
