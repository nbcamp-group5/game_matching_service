package com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatchingResultQueryDto {

    private Long resultId;
    private String discordUrl;
    private String playMode;
    private String gameInfo;
    private List<String> membersNickname = new ArrayList<>();
    public MatchingResultQueryDto(Long resultId,String playMode, String gameInfo,
                                  String discordUrl) {
        this.resultId = resultId;
        this.playMode = playMode;
        this.gameInfo = gameInfo;
        this.discordUrl = discordUrl;
    }
    public void addMember(List<String> membersNickname){
        this.membersNickname.addAll(membersNickname);
    }

}
