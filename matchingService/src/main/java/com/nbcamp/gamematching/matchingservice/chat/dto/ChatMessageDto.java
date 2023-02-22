package com.nbcamp.gamematching.matchingservice.chat.dto;

import lombok.Getter;

@Getter
public class ChatMessageDto {

    private String roomId;
    private String message;
    private String receiver;

}
