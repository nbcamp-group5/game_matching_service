package com.nbcamp.gamematching.matchingservice.matching.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.chat.dto.ResponseMatch;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public interface MatchingService {

    ResponseMatch joinMatchingRoom(RequestMatching requestMatching, HttpServletRequest servletRequest) throws JsonProcessingException;



}
