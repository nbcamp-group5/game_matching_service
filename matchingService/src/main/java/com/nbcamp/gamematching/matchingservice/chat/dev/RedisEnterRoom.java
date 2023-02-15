package com.nbcamp.gamematching.matchingservice.chat.dev;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisChatService implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;


    //조건 넣기: 방이 없으면 > 생성 // 방이 있으면 참여
    //친구이름


    @Override
    public void onMessage(Message message,byte[] pattern) {
        try{
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            Msg msg = objectMapper.readValue(publishMessage, Msg.class);
            simpMessagingTemplate.convertAndSend("/sub/room/1", msg);

        }catch(Exception e) {
            log.error(e.getMessage());
        }
    }
    public void enterRoom(String mmebername){
        ChannelTopic channelName = new ChannelTopic(mmebername);
        redisMessageListener.addMessageListener(redisEnterRoom, channelName);
    }
}
