package com.nbcamp.gamematching.matchingservice.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {

    private String boardImageUrl;
    private String content;

    public CreateBoardRequest(String boardImageUrl, String content) {
        this.boardImageUrl = boardImageUrl;
        this.content = content;
    }
}
