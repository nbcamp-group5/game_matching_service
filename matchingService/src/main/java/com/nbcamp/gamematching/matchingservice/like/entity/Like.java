package com.nbcamp.gamematching.matchingservice.like.entity;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public Like(Board board, Member member) {
        this.member = member;
        this.board = board;
    }

    public Like(Board board) {
        this.board = board;
    }
}
