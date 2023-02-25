package com.nbcamp.gamematching.matchingservice.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;

@Getter
@NoArgsConstructor
public class ResponseMatch {

    private String topicName;
    private String url;

    public ResponseMatch(String topicName) {
        this.topicName = topicName;
        this.url="";
    }
    public ResponseMatch(String topicName,String url) {
        this.topicName = topicName;
        this.url=url;
    }
}
