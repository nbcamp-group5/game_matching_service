package com.nbcamp.gamematching.matchingservice.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBoardRequest {

    private String BoardImageUrl;
    private String content;
    private String nickname;
}
