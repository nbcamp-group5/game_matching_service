package com.nbcamp.gamematching.matchingservice.member.dto;

//@NoArgsConstructor
//@Getter
//public class MatchingLogPageDto {
//
//    private Integer numberOfElements;
//    private Integer totalElements;
//    private Integer totalPages;
//    private Integer currentPage;
//    private List<MatchingLogContent> contents = new ArrayList<>();
//
//    @Builder
//    public MatchingLogPageDto(int numberOfElements, int totalElements, int totalPages,
//            List<MatchingLogContent> contents, int currentPage) {
//        this.numberOfElements = numberOfElements;
//        this.totalElements = totalElements;
//        this.totalPages = totalPages;
//        this.contents = contents;
//        this.currentPage = currentPage;
//    }
//
//
//    @Getter
//    public static class MatchingLogContent {
//
//        private Long id;
//        private List<String> nicknames;
//
//        public MatchingLogContent(MatchingLog matchingLog) {
//            this.id = matchingLog.getId();
//            this.nicknames = matchingLog.getMembers().stream()
//                    .map((member) -> member.getProfile().getNickname())
//                    .collect(Collectors.toList());
//        }
//    }
//}
