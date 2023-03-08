package com.nbcamp.gamematching.matchingservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseUrlInfo;
import com.nbcamp.gamematching.matchingservice.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static com.nbcamp.gamematching.matchingservice.redis.RedisService.objectMapper;

@RequiredArgsConstructor
@Component
@Slf4j
public class StompSessionInterceptor implements ChannelInterceptor {
    private final RedisService redisService;
    public static final Map<String, RequestMatching> connectedUserPool = new HashMap<>();

    @Override
    @Transactional
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        var sessionId = accessor.getSessionId();
        switch (accessor.getCommand()) {
            case SEND:
                if (accessor.getDestination().equals("/pub/chat/message")) {
                    break;
                }
                byte[] a = (byte[]) message.getPayload();
                String data = new String(a);
                try {
                    var member = objectMapper.readValue(data, ResponseUrlInfo.class);
                    connectedUserPool.put(sessionId, member.getMember());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }break;

            case DISCONNECT:
                var request = connectedUserPool.get(sessionId);
                redisService.matchingCancelByRedis(request);
                connectedUserPool.remove(sessionId);
                break;
        } return message;
    }
}