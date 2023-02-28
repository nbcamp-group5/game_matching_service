package com.nbcamp.gamematching.matchingservice.chat.repository.query;

import com.nbcamp.gamematching.matchingservice.chat.dto.ChatRoomDto;

public interface ChatRoomQueryRepository {

    ChatRoomDto findChatRoom(String roomName);
}
