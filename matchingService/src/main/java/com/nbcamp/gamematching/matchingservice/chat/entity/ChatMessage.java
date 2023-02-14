package com.nbcamp.gamematching.matchingservice.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private MessageType type;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;


    public void changeSender(String username) {
        this.sender = username;
    }
}

