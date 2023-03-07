package com.nbcamp.gamematching.matchingservice.matching.dto;

import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseUrlInfo {
    private Long matchingId;
    private RequestMatching member;
    private String topicName;
    private String url;

    public ResponseUrlInfo(Long matchingId, String url) {
        this.matchingId = matchingId;
        this.url = url;
    }

    public ResponseUrlInfo(RequestMatching member, String topicName) {
        this.member = member;
        this.topicName = topicName;
        this.url="";
    }
    @Builder
    public ResponseUrlInfo(Long matchingId,RequestMatching member, String topicName, String url) {
        this.matchingId = matchingId;
        this.member = member;
        this.topicName = topicName;
        this.url=url;
    }
}
