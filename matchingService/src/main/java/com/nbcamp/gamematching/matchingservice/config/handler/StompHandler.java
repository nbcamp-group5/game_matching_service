package com.nbcamp.gamematching.matchingservice.config.handler;

import com.nbcamp.gamematching.matchingservice.chat.repository.ChatRoomRepository;
import com.nbcamp.gamematching.matchingservice.chat.service.ChatService;
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Optional;


//WebSocket 연결 시, 요청 header의 jwt token 유효성 검증

@Slf4j
@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    public static final String TOKEN = "token";
    public static final String SIMP_DESTINATION = "simpDestination";
    public static final String SIMP_SESSION_ID = "simpSessionId";
    public static final String INVALID_ROOM_ID = "InvalidRoomId";

    private final MemberRepository memberRepository;
    private final ChatService chatService;
    private final ChatRoomRepository chatRoomRepository;

    private final ChannelTopic topic;



    //클라이언트가 메세지를 발송하면 해당 메세지를 인터셉트해서 처리
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        //최초 소켓 연결
        if (StompCommand.CONNECT == accessor.getCommand()) {
            String headerToken = accessor.getFirstNativeHeader(TOKEN);
            jwtUtil.validateToken(headerToken);
        }
        // SUBSCRIBE 등록 요청 - 채팅방 구독 요청
        else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {

            //토큰에서 사용자 정보 가져오기
            String headerToken = accessor.getFirstNativeHeader(TOKEN);
            jwtUtil.validateToken(headerToken);

            Member member = memberRepository.findByEmail(jwtUtil.getUserInfoFromToken(headerToken).getSubject())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

            String username = member.getProfile().getNickname();


            // header정보에서 구독 destination정보를 얻고, roomId를 추출한다.
            String roomId = chatService.getRoomId(Optional.ofNullable((String) message.getHeaders().get("simpDestination")).orElse("InvalidRoomId"));

            // 채팅방에 들어온 클라이언트 sessionId를 roomId와 맵핑해 놓는다.(나중에 특정 세션이 어떤 채팅방에 들어가 있는지 알기 위함)
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            chatRoomRepository.setUserEnterInfo(sessionId, roomId);


        }
        //소켓 연결 해제
        else if (StompCommand.DISCONNECT == accessor.getCommand()) {

            // 연결이 종료된 클라이언트 sesssionId로 채팅방 id를 얻는다.
            String sessionId = (String) message.getHeaders().get("simpSessionId");

            // 퇴장한 클라이언트의 roomId 맵핑 정보를 삭제한다.
            chatRoomRepository.removeUserEnterInfo(sessionId);
        }
        return message;
    }
}
