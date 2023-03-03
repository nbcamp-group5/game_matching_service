package com.nbcamp.gamematching.matchingservice.board.dto;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private final Long id;
    private final String nickname;
    private final String memberImage;
    private final String boardImage;
    private final String content;
    private Long likeCount;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;


    public BoardResponse(Board board,Long likeCount) {
        this.id = board.getId();
        this.nickname = board.getMember().getProfile().getNickname();
        this.memberImage = board.getMember().getProfile().getProfileImage();
        this.boardImage = board.getBoardImage();
        this.content = board.getContent();
        this.likeCount = likeCount;
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.nickname = board.getMember().getProfile().getNickname();
        this.memberImage = board.getMember().getProfile().getProfileImage();
        this.boardImage = board.getBoardImage();
        this.content = board.getContent();
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }


}
