package com.nbcamp.gamematching.matchingservice.board.dto;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnonymousBoardResponse {


    private final Long id;
    private final String nickname;
    private final String boardImage;
    private final String content;
    private Long likeCount;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;


    public AnonymousBoardResponse(AnonymousBoard board, Long likeCount) {
        this.id = board.getId();
        this.nickname = board.getNickname();
        this.boardImage = board.getBoardImage();
        this.content = board.getContent();
        this.likeCount = likeCount;
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }

    public AnonymousBoardResponse(AnonymousBoard board) {
        this.id = board.getId();
        this.nickname = board.getNickname();
        this.boardImage = board.getBoardImage();
        this.content = board.getContent();
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}

