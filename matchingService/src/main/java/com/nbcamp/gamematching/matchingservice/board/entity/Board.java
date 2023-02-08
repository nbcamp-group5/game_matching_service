package com.nbcamp.gamematching.matchingservice.board.entity;

import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="board_id")
    private Long id;

    private String nickname;
    //닉네임 익명처리??

    private String boardImageUrl;
    //이미지url?

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;
    //id 닉네임

    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Board(String boardImageUrl, String content) {
        this.boardImageUrl = boardImageUrl;
        this.content = content;
    }

    public Board(String nickname,String boardImageUrl, String content) {
        this.nickname = nNick(); //랜덤 닉네임...
        this.boardImageUrl = boardImageUrl;
        this.content = content;
    }

    public void updateBoard(UpdateBoardRequest boardRequestDto) {
        this.boardImageUrl = boardRequestDto.getBoardImageUrl();
        this.content = boardRequestDto.getContent();
    }
    public static String nNick() {
        List<String> 닉 = Arrays.asList("기분나쁜","기분좋은","신바람나는","상쾌한","짜릿한","그리운","자유로운","서운한","울적한","비참한","위축되는","긴장되는","두려운","당당한","배부른","수줍은","창피한","멋있는",
                "열받은","심심한","잘생긴","이쁜","시끄러운");
        List<String> 네임 = Arrays.asList("사자","코끼리","호랑이","곰","여우","늑대","너구리","침팬치","고릴라","참새","고슴도치","강아지","고양이","거북이","토끼","앵무새","하이에나","돼지","하마","원숭이","물소","얼룩말","치타",
                "악어","기린","수달","염소","다람쥐","판다");
        Collections.shuffle(닉);
        Collections.shuffle(네임);
        String text = 닉.get(0)+네임.get(0);
        return text;
    }


//좋아요

}
