package com.nbcamp.gamematching.matchingservice.board.dto;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.comment.dto.AnonymousCommentResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AnonymousBoardResponse {


    private final Long id;
    private final String nickname;
    private final String boardImageUrl;
    private final String content;
    private final List<AnonymousCommentResponse> comments;
    private final Long likeCount;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;


    public AnonymousBoardResponse(AnonymousBoard board, List<AnonymousCommentResponse> comments, Long likeCount) {
        this.id = board.getId();
        this.nickname = board.getNickname();
        this.boardImageUrl = board.getBoardImageUrl();
        this.content = board.getContent();
        this.comments = comments;
        this.likeCount = likeCount;
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}

