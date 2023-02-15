//package com.nbcamp.gamematching.matchingservice.chat.controller;
//
//
//import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
//import com.nbcamp.gamematching.matchingservice.chat.repository.ChatRoomRepository;
//import com.nbcamp.gamematching.matchingservice.chat.service.ChatService;
//import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
//import com.nbcamp.gamematching.matchingservice.member.entity.Member;
//import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Controller;
//
//import java.util.Optional;
//
//@Controller
//@RequiredArgsConstructor
//public class ChatController implements MessageListener {
//
//    private final JwtUtil jwtUtil;
//    private final RedisTemplate redisTemplate;
//
//    private final SimpMessageSendingOperations simpMessageSendingOperations;
//    private final ChannelTopic channelTopic;
//    private final MemberRepository memberRepository;
//
//
//    //websocket "/pub/chat/message"로 들어오는 메세지 처리
//    @MessageMapping("/chat/message")
//    public void onMessage(ChatMessage message, @Header("token") String token) {
//
//        Member member = memberRepository.findByEmail(jwtUtil.getUserInfoFromToken(token).getSubject())
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));
//
//        String username = member.getProfile().getNickname();
//        message.changeSender(username);
//
//
//
//        simpMessageSendingOperations.convertAndSend(channelTopic.getTopic(), message);
//
//    }
//}
//
//수정