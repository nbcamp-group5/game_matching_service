package com.nbcamp.gamematching.matchingservice.comment.dto;

import lombok.Getter;

@Getter
public class UpdateCommentRequest {

    private String content;

    public UpdateCommentRequest(String content) {
        this.content = content;
    }
}
