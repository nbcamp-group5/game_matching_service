package com.nbcamp.gamematching.matchingservice.matching.Service;

import static org.assertj.core.api.Assertions.assertThat;

import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.async.DeferredResult;

@SpringBootTest
class MatchingServiceImplTest {

    @Autowired
    private MatchingService matchingService;

    List<RequestMatching> requestMatchingList = new ArrayList<>();
    List<DeferredResult<MatchingLog>> deferredResultList = new ArrayList<>();

//    @BeforeEach
//    public void beforeEach() {
//
//        for (long i = 1; i < 6; i++) {
//            RequestMatching requestMatching = RequestMatching.builder()
//                    .gameMode("ㅈㄱ")
//                    .gameName("LOL")
//                    .memberNumbers("5")
//                    .memberId(i)
//                    .discordId("#2357" + i)
//                    .build();
//            requestMatchingList.add(requestMatching);
//            DeferredResult<MatchingLog> deferredResult = new DeferredResult<>(null);
//            deferredResultList.add(deferredResult);
//        }

//    }
//
//    @Test
//    @DisplayName("LOL 5인큐에서 5인 매칭 신청했을 때")
//    public void createMatchingRoomTest() {
//        // given
//
//        // when
//        for (int i = 0; i < 5; i++) {
//            matchingService.joinMatchingRoom(requestMatchingList.get(i), deferredResultList.get(i));
//        }
//        // then
//        Map<String, Map<RequestMatching, DeferredResult<MatchingLog>>> waitingQueue = matchingService.getWaitingQueue();
//        for (String s : waitingQueue.keySet()) {
//            System.out.println("waitingQueue.get(s) = " + waitingQueue.get(s).values().size());
//        }
//        assertThat(matchingService.getWaitingQueue().get("LOL5").values().size())
//                .isEqualTo(0);
//    }
//
//    @Test
//    @DisplayName("LOL 5인큐에서 3인만 매칭 신청했을 때")
//    public void notCreateMatchingRoomTest() {
//        // given
//
//        // when
//        for (int i = 0; i < 3; i++) {
//            matchingService.joinMatchingRoom(requestMatchingList.get(i), deferredResultList.get(i));
//        }
//        // then
//        Map<String, Map<RequestMatching, DeferredResult<MatchingLog>>> waitingQueue = matchingService.getWaitingQueue();
//        for (String s : waitingQueue.keySet()) {
//            System.out.println("waitingQueue.get(s) = " + waitingQueue.get(s).values().size());
//        }
//        assertThat(matchingService.getWaitingQueue().get("LOL5").values().size())
//                .isEqualTo(3);
//    }
//
//    @Test
//    @DisplayName("LOL 5인큐 신청")
//    public void joinMatchingRoomTest() throws Exception {
//        // given
//        RequestMatching requestMatching = requestMatchingList.get(0);
//        DeferredResult<MatchingLog> responseMatchingDeferredResult = deferredResultList.get(0);
//
//        // when
//        matchingService.joinMatchingRoom(requestMatching, responseMatchingDeferredResult);
//
//        // then
//        assertThat(matchingService.getWaitingQueue().get("LOL5").values().size()).isEqualTo(1);
//
//    }


}