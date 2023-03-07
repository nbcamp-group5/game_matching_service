package com.nbcamp.gamematching.matchingservice.comment.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

    @Column(nullable = false)
    private String content;

    public UpdateCommentRequest(String content) {
        this.content = content;
    }
}
