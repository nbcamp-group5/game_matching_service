package com.nbcamp.gamematching.matchingservice.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBoardRequest {

    private String content;

    public UpdateBoardRequest(String content) {
        this.content = content;
    }
}
