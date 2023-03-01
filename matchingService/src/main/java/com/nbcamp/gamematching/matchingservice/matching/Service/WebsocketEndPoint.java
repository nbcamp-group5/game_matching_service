package com.nbcamp.gamematching.matchingservice.matching.Service;

import com.nbcamp.gamematching.matchingservice.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
public class WebsocketEndPoint extends TextWebSocketHandler {

    private static Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String senderId = getMemberId(session);
        sessions.put(session.getId(),session); //리스트에 접속한 session들을 담음
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        String payloadMessage = message.getPayload();

        for (String key  : sessions.keySet()) {
            WebSocketSession webSocketSession = sessions.get(key);
         try {
            webSocketSession.sendMessage(new TextMessage("ECHO : " + payloadMessage));
         } catch (Exception e){
             e.printStackTrace();
         }

        }
    }

    //연결이 종료됐을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info(session.getId() + " 익셉션 발생: " + exception.getMessage());
    }

    private String getMemberId(WebSocketSession session) {
        Map<String, Object> httpSession = session.getAttributes();
        String email = (String) httpSession.get("email"); // 세션에 저장된 m_id 기준 조회
        return email==null? null: email;
    }
}
