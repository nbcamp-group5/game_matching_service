package com.nbcamp.gamematching.matchingservice.matching.entity;

import com.nbcamp.gamematching.matchingservice.matching.dto.MatchingStatusEnum;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    public MatchingLog(
            MatchingStatusEnum metchingEunm,
            String playMode,
            String gameName, String discordUrl, List<Member> matchingMemberList) {
        this.metchingEunm = metchingEunm;
        this.playMode = playMode;
        this.gameName = gameName;
        this.discordUrl = discordUrl;
        this.matchingMemberList = matchingMemberList;
    }

    @Builder
    public MatchingLog(
            MatchingStatusEnum metchingEunm,
            String playMode, String gameName) {
        this.metchingEunm = metchingEunm;
        this.playMode = playMode;
        this.gameName = gameName;
    }
}
