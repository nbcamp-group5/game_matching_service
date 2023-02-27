package com.nbcamp.gamematching.matchingservice.like.entity;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "AnonoymousLikes")
public class AnonymousLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anonymousLike_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anonymous_board_id")
    private AnonymousBoard anonymousBoard;

    public AnonymousLike(AnonymousBoard board, Member member) {
        this.member = member;
        this.anonymousBoard = board;
    }

    public void checkUser(AnonymousLike like, Member member) {
        if (!like.getMember().getEmail().equals(member.getEmail())) throw new IllegalArgumentException("유저 불일치");
    }
}
