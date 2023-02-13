package com.nbcamp.gamematching.matchingservice.matching.Service;

import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseMatching;
import org.springframework.web.context.request.async.DeferredResult;

public interface MatchingService {
    void joinMatchingRoom(RequestMatching requestMatching, DeferredResult<ResponseMatching> deferredResult);
    void MatchingCancel(RequestMatching request);
    void timeout(RequestMatching matchingRequest);

    void cratedMatchingRoom(RequestMatching requestMatching);

    void JoinResult(DeferredResult<ResponseMatching> result, ResponseMatching response);
}
