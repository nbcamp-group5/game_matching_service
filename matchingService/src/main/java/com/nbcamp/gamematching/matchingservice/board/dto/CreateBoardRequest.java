package com.nbcamp.gamematching.matchingservice.board.dto;

import lombok.Getter;

@Getter
public class CreateBoardRequest {

    private String content;

    public CreateBoardRequest(String content) {
        this.content = content;
    }
}
