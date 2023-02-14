package com.nbcamp.gamematching.matchingservice.matching.controller;


import com.nbcamp.gamematching.matchingservice.matching.Service.MatchingService;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseMatching;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/matching")
@Slf4j
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;

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