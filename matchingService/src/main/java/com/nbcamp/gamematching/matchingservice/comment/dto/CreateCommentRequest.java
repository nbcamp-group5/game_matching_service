package com.nbcamp.gamematching.matchingservice.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {

    private String content;

    public CreateCommentRequest(String content) {
        this.content = content;
    }
}
