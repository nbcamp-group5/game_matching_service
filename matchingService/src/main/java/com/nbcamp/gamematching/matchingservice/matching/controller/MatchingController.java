package com.nbcamp.gamematching.matchingservice.matching.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.matching.Service.MatchingService;
import com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto.MatchingResultQueryDto;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseUrlInfo;
import com.nbcamp.gamematching.matchingservice.matching.repository.MatchingLogRepository;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matching")
@Slf4j
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;
    private final SimpMessagingTemplate template;
    private final MatchingLogRepository matchingLogRepository;

    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<ResponseUrlInfo> joinRequest(@RequestBody RequestMatching requestMatching,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       HttpServletRequest servletRequest) throws JsonProcessingException {
        var member = userDetails.getMember();
        var matchingMember = new RequestMatching(requestMatching, member.getEmail());
        log.info("Join Matching Useremail{} UserDiscordId{}", member.getEmail(), requestMatching.getDiscordId());
        var urlInfo = matchingService.matchingJoin(matchingMember, servletRequest);
        return ResponseEntity.ok(urlInfo);
    }

    @MessageMapping(value = "/url")
    public void message(ResponseUrlInfo responseUrlInfo) {
        template.convertAndSend("/matchingsub/" + responseUrlInfo.getTopicName(), responseUrlInfo.getUrl());
    }

    @GetMapping("/findmember")
    @ResponseBody
    @Transactional(readOnly = true)
    public ResponseEntity<Optional<List<MatchingResultQueryDto>>> findByResultMatchingAndMember(@RequestParam Long id) {
        return ResponseEntity.ok(matchingLogRepository.findByMatchingResultMemberNicknameByMemberId(id));
    }

}