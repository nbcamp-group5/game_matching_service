package com.nbcamp.gamematching.matchingservice.member.dto;

import lombok.Getter;

@Getter
public class MannerPointsRequest {

    private Long matchingId;
    private Long targetId;
    private String upDown;

}
