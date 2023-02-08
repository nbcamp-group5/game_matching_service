package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.matchinglog.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.member.entity.Matching;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MatchingLogPageDto {

    private Integer numberOfElements;
    private Integer totalElements;
    private Integer totalPages;
    private Integer currentPage;
    private List<MatchingLogContent> contents = new ArrayList<>();

    @Builder
    public MatchingLogPageDto(int numberOfElements, int totalElements, int totalPages,
            List<MatchingLogContent> contents, int currentPage) {
        this.numberOfElements = numberOfElements;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.contents = contents;
        this.currentPage = currentPage;
    }


    @Data
    public static class MatchingLogContent {

        private Long id;
        private Member member;
        private Matching matching;

        public MatchingLogContent(MatchingLog matchingLog) {
            this.id = matchingLog.getId();
            this.member = matchingLog.getMember();
            this.matching = matchingLog.getMatching();
        }
    }
}
