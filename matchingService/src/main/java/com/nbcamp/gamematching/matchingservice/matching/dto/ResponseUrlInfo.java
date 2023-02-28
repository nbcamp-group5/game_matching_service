package com.nbcamp.gamematching.matchingservice.matching.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseUrlInfo {

    private String topicName;
    private String url;

    public ResponseUrlInfo(String topicName) {
        this.topicName = topicName;
        this.url="";
    }
    public ResponseUrlInfo(String topicName, String url) {
        this.topicName = topicName;
        this.url=url;
    }
}
