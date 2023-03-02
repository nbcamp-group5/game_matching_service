package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.matching.domain.MemberLog;
import java.util.List;
import lombok.Getter;

@Getter
public class MatchingLog5Dto {

    // List 로 내보냈을 때 프론트로 띄우는 방법을 몰라 이렇게 5개씩 나열합니다.
    private String nickname1;
    private String nickname2;
    private String nickname3;
    private String nickname4;

    private Long id1;
    private Long id2;
    private Long id3;
    private Long id4;

    private Long matchingId; //responseMatching ID

    public MatchingLog5Dto(List<MemberLog> memberAndLogs, Long matchingId) {
        this.nickname1 = memberAndLogs.get(0).getNickname();
        this.nickname2 = memberAndLogs.get(1).getNickname();
        this.nickname3 = memberAndLogs.get(2).getNickname();
        this.nickname4 = memberAndLogs.get(3).getNickname();

        this.id1 = memberAndLogs.get(0).getMemberId();
        this.id2 = memberAndLogs.get(1).getMemberId();
        this.id3 = memberAndLogs.get(2).getMemberId();
        this.id4 = memberAndLogs.get(3).getMemberId();

        this.matchingId = matchingId;
    }
}
