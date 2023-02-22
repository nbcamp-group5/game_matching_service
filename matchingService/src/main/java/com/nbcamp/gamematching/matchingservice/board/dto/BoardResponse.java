package com.nbcamp.gamematching.matchingservice.board.dto;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponse {

    private final Long id;
    private final String nickname;
    private final String boardImage;
    private final String content;
    private final List<CommentResponse> comments;
    private final Long likeCount;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;


    public BoardResponse(Board board, List<CommentResponse> comments, Long likeCount) {
        this.id = board.getId();
        this.nickname = board.getMember().getProfile().getNickname();
        this.boardImage = board.getBoardImage();
        this.content = board.getContent();
        this.comments = comments;
        this.likeCount = likeCount;
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}
