package com.nbcamp.gamematching.matchingservice.matching.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class ResponseMatching {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String playMode;
    @Column(nullable = false)
    private String gameName;
    @Column(nullable = false)
    private String discordUrl;


    @Builder
    public ResponseMatching(String playMode,
                       String gameName, String discordUrl) {
        this.playMode = playMode;
        this.gameName = gameName;
        this.discordUrl = discordUrl;
    }


}
