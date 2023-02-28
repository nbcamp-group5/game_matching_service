package com.nbcamp.gamematching.matchingservice.comment.entity;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.comment.dto.UpdateCommentRequest;
import com.nbcamp.gamematching.matchingservice.common.entity.BaseEntity;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class AnonymousComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    private String nickname;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anonymousBoard_id")
    private AnonymousBoard anonymousBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public AnonymousComment(String content, AnonymousBoard board, Member member) {
        this.content = content;
        this.anonymousBoard = board;
        this.member = member;
        this.nickname = nNick();
    }

    public void updateComment(UpdateCommentRequest updateCommentRequest,Member member) {
        this.content = updateCommentRequest.getContent();
        this.member = member;
    }

    public void checkUser(AnonymousComment comment, Member member) {
        if (!comment.getMember().getEmail().equals(member.getEmail())) throw new IllegalArgumentException("유저 불일치");
    }
    public static String nNick() {
        List<String> nick = Arrays.asList("기분나쁜", "기분좋은", "신바람나는", "상쾌한", "짜릿한", "그리운", "자유로운", "서운한", "울적한", "비참한", "위축되는", "긴장되는", "두려운", "당당한", "배부른", "수줍은", "창피한", "멋있는",
                "열받은", "심심한", "잘생긴", "이쁜", "시끄러운");
        List<String> name = Arrays.asList("사자", "코끼리", "호랑이", "곰", "여우", "늑대", "너구리", "침팬치", "고릴라", "참새", "고슴도치", "강아지", "고양이", "거북이", "토끼", "앵무새", "하이에나", "돼지", "하마", "원숭이", "물소", "얼룩말", "치타",
                "악어", "기린", "수달", "염소", "다람쥐", "판다");
        Collections.shuffle(nick);
        Collections.shuffle(name);
        String nickname = nick.get(0) + name.get(0);
        return nickname;
    }
}
