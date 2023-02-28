package com.nbcamp.gamematching.matchingservice.chat.controller;


import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import com.nbcamp.gamematching.matchingservice.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class ChatController{

    private final SimpMessagingTemplate template;
    private final ChatMessageRepository chatMessageRepository;

    //@MessageMapping을 통해서 websocket으로 들어오는 메세지 발행 처리
    //클라이언트가 "/pub/chat/enter" 발행 요청 시 controller가 처리
    //메세지가 발행되면 "sub/chat/room/{roomId}로 전송

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessage chatMessage){
        chatMessageRepository.save(chatMessage);
        template.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
    }

}