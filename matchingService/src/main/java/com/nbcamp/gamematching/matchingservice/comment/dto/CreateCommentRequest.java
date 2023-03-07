package com.nbcamp.gamematching.matchingservice.comment.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {

    @Column(nullable = false)
    private String content;

    public CreateCommentRequest(String content) {
        this.content = content;
    }
}
