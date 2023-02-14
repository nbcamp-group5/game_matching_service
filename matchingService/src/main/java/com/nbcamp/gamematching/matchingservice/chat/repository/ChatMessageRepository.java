package com.nbcamp.gamematching.matchingservice.chat.repository;

import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
