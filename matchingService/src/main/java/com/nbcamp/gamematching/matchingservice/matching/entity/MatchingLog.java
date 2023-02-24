package com.nbcamp.gamematching.matchingservice.matching.entity;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class MatchingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_matching_id")
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private MatchingStatusEnum metchingEunm;
    @Column(nullable = false)
    private String playMode;
    @Column(nullable = false)
    private String gameName;
    @Column(nullable = false)
    private String discordUrl;
    @OneToMany
    private List<Member> matchingMemberList = new ArrayList<>();

    @Builder
    public MatchingLog(MatchingStatusEnum metchingEunm, String playMode,
                       String gameName, String discordUrl, List<Member> matchingMemberList) {
        this.metchingEunm = metchingEunm;
        this.playMode = playMode;
        this.gameName = gameName;
        this.discordUrl = discordUrl;
        this.matchingMemberList = matchingMemberList;
    }
    @Builder
    public MatchingLog(MatchingStatusEnum metchingEunm, String playMode, String gameName) {
        this.metchingEunm = metchingEunm;
        this.playMode = playMode;
        this.gameName = gameName;
    }
}
