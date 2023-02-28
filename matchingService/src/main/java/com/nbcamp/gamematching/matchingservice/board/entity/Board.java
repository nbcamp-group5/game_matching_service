package com.nbcamp.gamematching.matchingservice.board.entity;

import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.common.entity.BaseEntity;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="board_id")
    private Long id;
    private String nickname;

    private String boardImageUrl;
    //이미지url?

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Board(String nickname,String boardImageUrl, String content,Member member) {
        this.nickname = member.getProfile().getNickname();
        this.boardImageUrl = boardImageUrl;
        this.content = content;
        this.member = member;
    }

    public void updateBoard(UpdateBoardRequest boardRequestDto,Member member) {
        this.boardImageUrl = boardRequestDto.getBoardImageUrl();
        this.content = boardRequestDto.getContent();
        this.member = member;
    }

    public void checkUser(Board board, Member member) {
        if (!board.getMember().getEmail().equals(member.getEmail())) throw new IllegalArgumentException("유저 불일치");
    }

}
