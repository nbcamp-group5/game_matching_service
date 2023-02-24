package com.nbcamp.gamematching.matchingservice.matching.controller;

import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingMessage;
import com.nbcamp.gamematching.matchingservice.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchingRoomController {

    private final RedisService redisService;

    @MessageMapping("/matching/url")
    public void message(MatchingMessage message) {
        var topic = message.getMatchingRoomName();
        redisService.publishUrl(redisService.getTopic(topic), message);
    }




}
