package com.nbcamp.gamematching.matchingservice.comment.dto;

import com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnonymousCommentResponse {
    private final Long id;
    private final String nickname;
    private final String content;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public AnonymousCommentResponse(AnonymousComment comment) {
        this.id = comment.getId();
        this.nickname = comment.getNickname();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
