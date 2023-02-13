package com.nbcamp.gamematching.matchingservice.redis;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public interface RedisService {

    String getRedisStringValue(String key);
    String getRedisStringKey(String value);
    void setRedisStringValue(String key, String value, Duration duration);
    void deleteRedisValue(String key);
    void RedisLogoutAccessToken(String accessToken, String logout, Long expiretime, TimeUnit milliseconds);
}
