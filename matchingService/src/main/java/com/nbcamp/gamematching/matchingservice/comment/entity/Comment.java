package com.nbcamp.gamematching.matchingservice.comment.entity;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.comment.dto.UpdateCommentRequest;
import com.nbcamp.gamematching.matchingservice.common.entity.BaseEntity;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String nickname;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Comment(String content, Board board, Member member) {
        this.content = content;
        this.board = board;
        this.member = member;
        this.nickname = member.getProfile().getNickname();
    }

    public void updateComment(UpdateCommentRequest updateCommentRequest, Member member) {
        this.content = updateCommentRequest.getContent();
        this.member = member;
    }

    public void checkUser(Comment comment, Member member) {
        if (!comment.getMember().getEmail().equals(member.getEmail())) throw new IllegalArgumentException("유저 불일치");
    }
}
