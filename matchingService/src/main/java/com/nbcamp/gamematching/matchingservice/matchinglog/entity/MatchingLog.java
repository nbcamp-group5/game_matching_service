package com.nbcamp.gamematching.matchingservice.matchinglog.entity;

import com.nbcamp.gamematching.matchingservice.matching.entity.Matching;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class MatchingLog {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "matching_id")
    private Matching matching;
}
