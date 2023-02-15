package com.nbcamp.gamematching.matchingservice.matching.Service;

import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseMatching;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.async.DeferredResult;

@SpringBootTest
class MatchingServiceImplTest {

    @Autowired
    private MatchingService matchingService;

    @BeforeEach
    public void beforeEach() {
        List<RequestMatching> requestMatchingList = new ArrayList<>();
        List<DeferredResult<ResponseMatching>> deferredResultList = new ArrayList<>();

        for (long i = 1; i < 6; i++) {
            RequestMatching requestMatching = RequestMatching.builder()
                    .gameMode("enjoy")
                    .gamename("LOL")
                    .discordUrl("/discord_url" + i)
                    .memberNumbers("5")
                    .memberId(i)
                    .discordId("#2357" + i)
                    .build();
            requestMatchingList.add(requestMatching);
            DeferredResult<ResponseMatching> deferredResult = new DeferredResult<>(null);
            deferredResultList.add(deferredResult);
        }

    }

    @Test
    public void createMatchingRoomTest() throws Exception {
        // given

        // when

        // then

    }


}