package com.nbcamp.gamematching.matchingservice.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBoardRequest {

    private String boardImageUrl;
    private String content;
}
