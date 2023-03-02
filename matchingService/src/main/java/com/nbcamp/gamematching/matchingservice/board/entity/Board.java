package com.nbcamp.gamematching.matchingservice.board.entity;

import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.common.entity.BaseEntity;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;
    private String nickname;

    @Column(name = "board_image")
    private String boardImage;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Board(String nickname, String boardImage, String content, Member member) {
        this.nickname = member.getProfile().getNickname();
        this.boardImage = boardImage;
        this.content = content;
        this.member = member;
    }

    public void updateBoard(UpdateBoardRequest boardRequestDto, String boardImageUrl, Member member)
            throws IOException {
        this.boardImage = boardImageUrl;
        this.content = boardRequestDto.getContent();
        this.member = member;
    }

    public void checkUser(Board board, Member member) {
        if (!board.getMember().getEmail().equals(member.getEmail())) {
            throw new IllegalArgumentException("유저가 일치하지 않습니다.");
        }
    }
}
