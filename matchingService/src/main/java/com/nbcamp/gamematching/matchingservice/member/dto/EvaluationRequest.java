package com.nbcamp.gamematching.matchingservice.member.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class EvaluationRequest {

    private Long matchingId; // responseMatching ID
    private List<MannerPointsRequest> requests;
}
