package com.nbcamp.gamematching.matchingservice.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatRoom;
import com.nbcamp.gamematching.matchingservice.chat.repository.ChatRoomRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final ChatRoomRepository chatRoomRepository;

    /**
     * destination정보에서 roomId 추출
     */
    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    /**
     * 채팅방에 메시지 발송
     */
    public void sendChatMessage(ChatMessage chatMessage) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }



}
