package com.nbcamp.gamematching.matchingservice.member.dto;

import lombok.Getter;

@Getter
public class EvaluationOneMember {
    private Long matchingId;
    private Long targetId;
    private String upDown;
}
