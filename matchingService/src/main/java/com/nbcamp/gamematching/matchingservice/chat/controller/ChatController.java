package com.nbcamp.gamematching.matchingservice.chat.controller;


import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import com.nbcamp.gamematching.matchingservice.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatController{

    private final SimpMessagingTemplate template;
    private final ChatMessageRepository chatMessageRepository;

    //@MessageMapping을 통해서 websocket으로 들어오는 메세지 발행 처리
    //클라이언트가 "/pub/chat/enter" 발행 요청 시 controller가 처리
    //메세지가 발행되면 "sub/chat/room/{roomId}로 전송

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        chatMessageRepository.save(message);
    }

//    private final SimpMessageSendingOperations simpMessageSendingOperations;
//
//
//    //websocket "/pub/chat/message"로 발행 요청이 들어오면 controller에서 처리
//    //메세지가 발행되면 "/sub/chat/room/{roomId}"로 메세지 보냄
//    //클라이언트는 위의 주소를 구독하고 있다가 전달되면 화면에 출력
//    @MessageMapping("/chat/message")
//    public void message(ChatMessage message) {
//        //메세지가 사용자에게 발행됨
//        simpMessageSendingOperations.convertAndSend("/sub/chat/room" + message.getRoomId(), message);

//    }
}