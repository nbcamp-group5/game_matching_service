package com.nbcamp.gamematching.matchingservice.chat.dev;


import com.example.board.entity.ChatRoom;
import com.example.board.entity.Msg;
import com.example.board.redis.RedisChatService;
import com.example.board.security.UserDetailsImpl;
import com.example.board.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final ChatService chatService;
    private Map<String, ChannelTopic> channels = new HashMap<>();
    private final RedisChatService redisChatService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisMessageListenerContainer redisMessageListener;

    // 신규 Topic을 생성하고 Listener등록 및 Topic Map에 저장

    @PutMapping("/room")
    @Transactional
    public void createRoom(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        redisChatService.enterRoom(userDetails.getUser());

//        channels.put("a", channel);
    }
    @MessageMapping("/room/1")
    public void pushMessage( @RequestParam String message) {
        ChannelTopic channel = channels.get("a");
        publish(channel, Msg.builder().roomId("a").message(message).build());
    }


    public void publish(ChannelTopic topic, Msg msg) {
        redisTemplate.convertAndSend(topic.getTopic(), msg);
    }
    // 메세지를 요청 보내면 > 컨트롤러  > 퍼블리셔 > 레디스로 > 구독자들에게
    //   sub              sub      pub       pub     pub

//
//    @PostMapping("/newroom")
//    public ResponseEntity<ChatRoom> createRoom(@RequestBody RoomRequestDto roomRequestDto,
//                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return ResponseEntity.ok().body(chatService.createRoom(roomRequestDto, userDetails.getUser()));
//    }


    @PostMapping("/room/{title}/enter")  //            /api/chat/room/ddd/enter
    public ResponseEntity<?> enterRoom(@PathVariable String title,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ChatRoom enteredRoom = chatService.enterRoom(title, userDetails.getUser());
        // redis 방입장 구독
        return ResponseEntity.ok().body(enteredRoom);
    }

    @PostMapping("/room/{title}/exit")
    public ResponseEntity<?> exitRoom(@PathVariable String title,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        chatService.exitRoom(title, userDetails.getUser());
        return ResponseEntity.ok().body(null);
    }

}
