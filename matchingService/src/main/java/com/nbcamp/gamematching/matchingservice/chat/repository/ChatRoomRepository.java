package com.nbcamp.gamematching.matchingservice.chat.repository;

import com.nbcamp.gamematching.matchingservice.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    boolean existsByRoomName(String roomName);

    ChatRoom findByRoomName(String roomName);
}
