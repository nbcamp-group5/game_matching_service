package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import java.util.ArrayList;
import java.util.List;

public class BuddyPageDto {

    private Integer numberOfElements;
    private Integer totalElements;
    private Integer totalPages;
    private Integer currentPage;
    private List<BuddyContent> contents = new ArrayList<>();

    public BuddyPageDto(Integer numberOfElements, Integer totalElements, Integer totalPages,
            Integer currentPage, List<BuddyContent> contents) {
        this.numberOfElements = numberOfElements;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.contents = contents;
    }

    public static class BuddyContent {

        private Profile profile;
        private String email;

        public BuddyContent(Member member) {
            this.profile = member.getProfile();
            this.email = member.getEmail();
        }
    }
}
