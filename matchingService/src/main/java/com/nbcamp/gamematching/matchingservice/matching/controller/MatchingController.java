package com.nbcamp.gamematching.matchingservice.matching.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException;
import com.nbcamp.gamematching.matchingservice.matching.Service.MatchingService;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.redis.RedisService;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matching")
@Slf4j
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;
    private final RedisService redisService;

    @PostMapping("/join")
    @ResponseBody
    public String joinRequest(@RequestBody RequestMatching requestMatching,
                              @AuthenticationPrincipal UserDetailsImpl userDetails,
                              HttpServletRequest servletRequest) throws JsonProcessingException {
        var member = userDetails.getMember();
        var requestmember = new RequestMatching(requestMatching,member.getId());
        matchingService.joinMatchingRoom(requestmember,servletRequest);
        log.info("Join Matching Useremail{} UserDiscordId{}",member.getEmail(),requestMatching.getDiscordId());
        return "redirect:/";
    }

    @GetMapping("/cancel")
    @ResponseBody
    public String matchingCancel(HttpServletRequest servletRequest) {
        //받아오는 값에 RequestMatching 없으면 예외처리 발생
        var session = servletRequest.getSession();
        var sessionInfo = (RequestMatching) session.getAttribute("UserSession");
        if (sessionInfo == null){
            log.info(" = session is null = ");
            throw new NotFoundException.NotFoundMemberException();
        }
        redisService.matchingCancelByRedis(sessionInfo);
        log.info(" = Matching Cancel Success= ");
        return "redirect:/";
    }



}