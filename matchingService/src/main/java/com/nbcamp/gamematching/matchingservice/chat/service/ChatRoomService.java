package com.nbcamp.gamematching.matchingservice.chat.service;

import com.nbcamp.gamematching.matchingservice.chat.dto.ChatRoomDto;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatRoomService {


    ResponseEntity<List<BuddyDto>> getFriends(Member member);

    ResponseEntity<ChatRoomDto> getChatRoom(String friendNick, Member member);

    List<ChatMessage> getChatMessage(Long roomId);
}
