package com.nbcamp.gamematching.matchingservice.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBoardRequest {

    private String BoardImageUrl;
    private String content;
}
