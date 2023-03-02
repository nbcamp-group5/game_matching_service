package com.nbcamp.gamematching.matchingservice.matching.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseUrlInfo {

    private RequestMatching member;
    private String topicName;
    private String url;

    public ResponseUrlInfo(RequestMatching member,String topicName) {
        this.member = member;
        this.topicName = topicName;
        this.url="";
    }
    @Builder
    public ResponseUrlInfo(RequestMatching member,String topicName, String url) {
        this.member = member;
        this.topicName = topicName;
        this.url=url;
    }
}
