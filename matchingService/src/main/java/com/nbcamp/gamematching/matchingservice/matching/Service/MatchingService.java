package com.nbcamp.gamematching.matchingservice.matching.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto.MatchingResultQueryDto;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseUrlInfo;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface MatchingService {

    ResponseUrlInfo matchingJoin(RequestMatching requestMatching,
                                 HttpServletRequest servletRequest) throws JsonProcessingException;
    Optional<List<MatchingResultQueryDto>> findByMatchingResultMemberNiccknameByMemberId(Long id);

}
