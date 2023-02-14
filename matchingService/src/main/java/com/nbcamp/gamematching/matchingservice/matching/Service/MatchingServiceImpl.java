package com.nbcamp.gamematching.matchingservice.matching.Service;


import com.nbcamp.gamematching.matchingservice.discord.service.DiscordService;
import com.nbcamp.gamematching.matchingservice.matching.dto.MatchingStatusEnum;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseMatching;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {
    private static final Logger logger = LoggerFactory.getLogger(MatchingService.class);
    private Map<String, Map<RequestMatching, DeferredResult<ResponseMatching>>> waitingQueue;
    // {key : websocket session id, value : chat room id}
    private ReentrantReadWriteLock lock;
    private final DiscordService discordService;

    @PostConstruct
    private void setUp() {
        this.lock = new ReentrantReadWriteLock();
        this.waitingQueue = new Hashtable<>();
    }
    @Async("asyncThreadPool")
    public void joinMatchingRoom(RequestMatching request, DeferredResult<ResponseMatching> deferredResult) {
        logger.info("## Join chat room request. {}[{}]", Thread.currentThread().getName(), Thread.currentThread().getId());
        if (request == null || deferredResult == null) {
            return;
        }
        try {
            lock.writeLock().lock();
            if (waitingQueue.containsKey(request.getKey())) {//이미 생성된 키가 있으면
                waitingQueue.get(request.getKey()).put(request, deferredResult);
            } else {
                Map<RequestMatching, DeferredResult<ResponseMatching>> newUserPool = new LinkedHashMap<>();
                newUserPool.put(request, deferredResult);
                waitingQueue.put(request.getKey(), newUserPool);
            }
            //waitingUsers.put(request, deferredResult);
        } finally {
            lock.writeLock().unlock();
            cratedMatchingRoom(request);
        }
    }

    public void MatchingCancel(RequestMatching request) {
        try {
            lock.writeLock().lock();
            JoinResult(waitingQueue.get(request.getKey()).remove(request),
                    new ResponseMatching(MatchingStatusEnum.TIMEOUT,request.getGameMode(),request.getGameName()));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void timeout(RequestMatching request) {
        try {
            lock.writeLock().lock();
            JoinResult(waitingQueue.get(request.getKey()).remove(request),
                    new ResponseMatching(MatchingStatusEnum.TIMEOUT,request.getGameMode(),request.getGameName()));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void cratedMatchingRoom(RequestMatching request) {
        try {
            logger.debug("현재 대기 유저 : " + waitingQueue.get(request.getKey()).size());
            lock.readLock().lock();
            if (waitingQueue.get(request.getKey()).size() < Integer.parseInt(request.getMemberNumbers())) {//유저가 특정수 이하 면 컷
                return;
            }
            Iterator<RequestMatching> itr = waitingQueue.get(request.getKey()).keySet().iterator();
            List<RequestMatching> roomUserKey = new ArrayList<>();
            List<DeferredResult<ResponseMatching>> roomUserValue = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(request.getMemberNumbers()); i++) {
                roomUserKey.add(itr.next());
            }

            List<String> discordIdList = new ArrayList<>();
            for (RequestMatching matchingRequest : roomUserKey) {
                discordIdList.add(matchingRequest.getDiscordId());
            }
            // 방을 생성하고 초대코드를 가져온다
            Optional<String> getUrl = discordService.createChannel(request.getGameMode(),discordIdList);

            String url = "";
            if (getUrl.isPresent()) {
                System.out.println(url);
                url = getUrl.get();
            } else {
                // url을 못갖고 오는 경우를 처리
                return;
            }

            for (int i = 0; i < Integer.parseInt(request.getMemberNumbers()); i++) {
                roomUserValue.add(waitingQueue.get(request.getKey()).remove(roomUserKey.get(i)));
            }
            for (int i = 0; i < Integer.parseInt(request.getMemberNumbers()); i++) {
                roomUserValue.get(i).setResult(
                        ResponseMatching.builder()
                        .metchingEunm(MatchingStatusEnum.SUCCESS)
                        .playMode(request.getGameMode())
                        .gameName(request.getGameMode())
                        .memberNumbers(request.getMemberNumbers())
                        .discordUrl(url)
                        .members(roomUserKey)
                        .build());}
        } catch (Exception e) {
            logger.warn("Exception occur while checking waiting users", e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void JoinResult(DeferredResult<ResponseMatching> result, ResponseMatching response) {
        if (result != null) {
            result.setResult(response);
        }
    }
}