package com.nbcamp.gamematching.matchingservice.matching.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
public class MatchingMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matchingRoomName;
    private String email;
    private String url;

    @Builder
    public MatchingMessage(String email, String url, String matchingRoomName) {
        this.url = url;
        this.email= email;
        this.matchingRoomName= matchingRoomName;
    }
}