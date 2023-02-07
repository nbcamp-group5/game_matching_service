package com.nbcamp.gamematching.matchingservice.member.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor
@Getter
public class PageDto {

    private Integer numberOfElements;
    private Integer totalElements;
    private Integer totalPages;
    private Integer currentPage;
    private List<Content> contents = new ArrayList<>();

    @Builder
    public PageDto(int numberOfElements, int totalElements, int totalPages,
            List<Content> contents, int currentPage) {
        this.numberOfElements = numberOfElements;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.contents = contents;
        this.currentPage = currentPage;
    }

    public static Pageable toPageable(Integer currentPage, Integer size) {
        return PageRequest.of((currentPage - 1), size);
    }

    public static class Content {

        private Long id;
        private String nickname;
        private String boardImageUrl;
        private String Content;

        @Builder
        public Content(Long id, String nickname, String boardImageUrl, String content) {
            this.id = id;
            this.nickname = nickname;
            this.boardImageUrl = boardImageUrl;
            Content = content;
        }
    }
}
