package com.nbcamp.gamematching.matchingservice.matching.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseUrlInfo;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;

import jakarta.servlet.http.HttpServletRequest;

public interface MatchingService {

    ResponseUrlInfo matchingJoin(RequestMatching requestMatching, HttpServletRequest servletRequest) throws JsonProcessingException;


}
