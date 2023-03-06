package com.nbcamp.gamematching.matchingservice.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.member.dto.EvaluationOneMember;
import com.nbcamp.gamematching.matchingservice.member.dto.EvaluationRequest;
import org.springframework.http.ResponseEntity;

public interface MannerPointsService {
    ResponseEntity<String> changeMannerPointsByOne(EvaluationOneMember request, Long memberId);

    ResponseEntity<String> changeMannerPoints(EvaluationRequest request, Long memberId)
            throws JsonProcessingException;
}
