package com.nbcamp.gamematching.matchingservice.like.entity;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class AnonymousLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "anonymousLike_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anonymousBoard_id")
    private AnonymousBoard anonymousBoard;

    public AnonymousLikes(AnonymousBoard board, Member member) {
        this.member = member;
        this.anonymousBoard = board;
    }

    public void checkUser(AnonymousLikes likes, Member member) {
        if (!likes.getMember().getEmail().equals(member.getEmail())) throw new IllegalArgumentException("유저 불일치");
    }
}
