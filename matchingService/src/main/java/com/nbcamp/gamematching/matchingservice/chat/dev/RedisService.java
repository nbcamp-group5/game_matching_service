package com.nbcamp.gamematching.matchingservice.chat.dev;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public String getRedisStringValue(String key) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        System.out.println("Redis key : " + key);
        System.out.println("Redis value : " + stringStringValueOperations.get(key));
        return stringStringValueOperations.get(key);
    }
    public String getRedisStringKey(String value) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        return stringStringValueOperations.get(value);
    }

    public void setRedisStringValue(String key, String value, Duration duration) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(key, value, duration);
        System.out.println("Redis key : " + key);
        System.out.println("Redis value : " + stringValueOperations.get(key));
    }

    public void deleteRedisValue(String key){
        stringRedisTemplate.delete(key);
    }

    public void RedisLogoutAccessToken(String accessToken, String logout, Long expiretime, TimeUnit milliseconds) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(accessToken, logout, expiretime,milliseconds);
        System.out.println("Redis key : " + accessToken);
        System.out.println("Redis value : " + stringValueOperations.get(logout));
    }

}
