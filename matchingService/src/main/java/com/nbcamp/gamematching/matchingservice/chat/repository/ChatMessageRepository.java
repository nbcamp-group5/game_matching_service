package com.nbcamp.gamematching.matchingservice.chat.repository;

import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByRoomId(Long roomId);
}
