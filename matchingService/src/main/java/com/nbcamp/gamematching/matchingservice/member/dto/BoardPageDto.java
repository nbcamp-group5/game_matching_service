package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.entity.Board;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardPageDto {

    private Integer numberOfElements;
    private Integer totalElements;
    private Integer totalPages;
    private Integer currentPage;
    private List<BoardContent> contents = new ArrayList<>();

    @Builder
    public BoardPageDto(int numberOfElements, int totalElements, int totalPages,
            List<BoardContent> contents, int currentPage) {
        this.numberOfElements = numberOfElements;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.contents = contents;
        this.currentPage = currentPage;
    }


    @Getter
    public static class BoardContent {

        private Long id;
        private String nickname;
        private String boardImageUrl;
        private String contents;

        public BoardContent(Board board) {
            this.id = board.getId();
            this.nickname = board.getNickname();
            this.boardImageUrl = board.getBoardImageUrl();
            contents = board.getContent();
        }
    }
}
