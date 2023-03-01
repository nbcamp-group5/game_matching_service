package com.nbcamp.gamematching.matchingservice.matching.entity;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class MatchingLog {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "responsematching_id")
    private ResultMatching resultMatching;


    public MatchingLog(ResultMatching resultMatching, Member member) {
        this.resultMatching = resultMatching;
        this.member = member;
    }
}
