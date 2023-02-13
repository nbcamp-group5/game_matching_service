package com.nbcamp.gamematching.matchingservice.matching.controller;


import com.nbcamp.gamematching.matchingservice.matching.Service.MatchingService;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseMatching;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@CrossOrigin("*")
@RequestMapping("/matching")
@Slf4j
public class MatchingController {


    @Autowired
    private MatchingService matchingService;

    @GetMapping("/join")
    @ResponseBody
    public DeferredResult<ResponseMatching> joinRequest(RequestMatching requestMatching) {
        final DeferredResult<ResponseMatching> deferredResult = new DeferredResult<>(null); //비동기 프로세스 생성.
        matchingService.joinMatchingRoom(requestMatching, deferredResult);
        deferredResult.onCompletion(() -> matchingService.MatchingCancel(requestMatching));//에러 확인되면 채팅룸 닫기.
        deferredResult.onError((throwable) -> matchingService.MatchingCancel(requestMatching));
        deferredResult.onTimeout(() -> matchingService.timeout(requestMatching));
        return deferredResult;
    }

    @GetMapping("/cancel")
    @ResponseBody
    public ResponseEntity<Void> MatchingCancel(RequestMatching requestMatching) {
        matchingService.MatchingCancel(requestMatching);
        return ResponseEntity.ok().build();
    }

}