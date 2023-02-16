package com.nbcamp.gamematching.matchingservice.chat.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import com.nbcamp.gamematching.matchingservice.chat.entity.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

//발행된 메세지를 subscriber가 받아서 처리
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final ChannelTopic channelTopic;
    private final StringRedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;



    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatMessage msg = objectMapper.readValue(publishMessage, ChatMessage.class);
            log.debug("RedisSubscriber : " + msg.toString());
            if (msg.getType() == MessageType.ENTER) {
                messagingTemplate.convertAndSend(channelTopic.getTopic(), msg);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
