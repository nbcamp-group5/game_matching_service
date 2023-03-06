package com.nbcamp.gamematching.matchingservice.member.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import lombok.Getter;

@Getter
public class EvaluationRequest {

    private Long matchingId; // responseMatching ID
    private String requests;
}
