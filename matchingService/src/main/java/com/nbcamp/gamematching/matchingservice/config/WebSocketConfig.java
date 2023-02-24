package com.nbcamp.gamematching.matchingservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.logging.Handler;

@Configuration
@EnableWebSocketMessageBroker
//상속을 통해 stomp로 메시지 처리 방법 구성
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //메세지를 중간에서 라우팅하는 메시지 브로커 구성
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //메세지를 수신하는 요청의 prefix를 /sub으로 시작하도록 설정
        config.enableSimpleBroker("/sub","/matchingsub");
        //메세지를 발신하는 요청의 prefix를 /pub으로 시작하도록 설정
        config.setApplicationDestinationPrefixes("/pub","/matchingpub");
    }

    //클라이언트에서 websocket에 접속할 수 있는 endpoint 지정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/GGTalk","/GGMatching")
                .setAllowedOriginPatterns("*") // 이후 수정
                .withSockJS();
    }

}
