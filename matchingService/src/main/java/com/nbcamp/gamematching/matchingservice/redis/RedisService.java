package com.nbcamp.gamematching.matchingservice.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisService {

    void machedEnterByRedis(String key, RequestMatching value) throws JsonProcessingException;
    Long waitingUserCountByRedis(String key);
    List<RequestMatching> getMatchingMemberByRedis(String key, Long limit, Class<RequestMatching> count);
    void matchingCancelByRedis(RequestMatching member);
    void addRefreshTokenByRedis(String email, String refreshToken, Duration duration);
    void logoutAccessTokenByRedis(String accessToken, String logout, Long expiretime, TimeUnit milliseconds);
    void deleteRefreshTokenByRedis(String email);
}
