package com.nbcamp.gamematching.matchingservice.chat.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

//발행된 메세지를 subscriber가 받아서 처리
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    //redis에서 메세지 발행되면 대기하고 있던 redis subscriber가 메세지를 받아 처리
    public void sendMessage(String publishMessage) {
        try {
            //발행된 메세지를 객체 형태로 바꿈
            ChatMessage chatMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
            //채팅방을 구독한 클라이언트에 메세지 발송
            messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
            ChatMessage message = new ChatMessage();

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }

}
