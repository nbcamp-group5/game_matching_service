package com.nbcamp.gamematching.matchingservice.member.dto;


import com.nbcamp.gamematching.matchingservice.matching.domain.MemberLog;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class MatchingMemberDto {

    private List<String> nicknames = new ArrayList<>();

    private Long matchingId; //responseMatching ID

    public MatchingMemberDto(List<String> nicknames, Long matchingId) {
        nicknames.stream().forEach(this::addNicknames);
        this.matchingId = matchingId;
    }

    public void addNicknames(String nickname) {
        this.nicknames.add(nickname);
    }
}
