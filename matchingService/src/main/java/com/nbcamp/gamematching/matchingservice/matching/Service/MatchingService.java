package com.nbcamp.gamematching.matchingservice.matching.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.matching.dto.NicknameDto;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseUrlInfo;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;

import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface MatchingService {

    ResponseUrlInfo matchingJoin(RequestMatching requestMatching,
                                 HttpServletRequest servletRequest) throws JsonProcessingException;
    Optional<List<MatchingResultQueryDto>> findByMatchingResultMemberNiccknameByMemberId(Long id);

    List<NicknameDto> findMatchingMembers(Long matchingId, Long memberId);

    ResultMatching findResultMatchingById(Long matchingId);
}
