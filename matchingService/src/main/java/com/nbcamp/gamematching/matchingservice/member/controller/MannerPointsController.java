package com.nbcamp.gamematching.matchingservice.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.member.dto.EvaluationOneMember;
import com.nbcamp.gamematching.matchingservice.member.dto.EvaluationRequest;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.service.MannerPointsService;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mannerPoints")
@RequiredArgsConstructor
public class MannerPointsController {

    private final MannerPointsService mannerPointsService;

    @PostMapping("/evaluation/matching2")
    public ResponseEntity<String> changeMannerPointsByOne(@RequestBody EvaluationOneMember request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return mannerPointsService.changeMannerPointsByOne(request, member.getId());
    }

    @PostMapping("/evaluation/matching5")
    public ResponseEntity<String> changeMannerPoints(@RequestBody EvaluationRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws JsonProcessingException {
        Member member = userDetails.getMember();
        return mannerPointsService.changeMannerPoints(request, member.getId());
    }

}
