package com.nbcamp.gamematching.matchingservice.matching.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class ResultMatching {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String playMode;
    @Column(nullable = false)
    private String gameInfo;
    @Column(nullable = false)
    private String discordUrl;


    @Builder
    public ResultMatching(String playMode,
                          String gameInfo, String discordUrl) {
        this.playMode = playMode;
        this.gameInfo = gameInfo;
        this.discordUrl = discordUrl;
    }


}
