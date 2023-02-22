package com.nbcamp.gamematching.matchingservice.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void machedEnterByRedis(String email, RequestMatching member) throws JsonProcessingException {
        var mappperv = objectMapper.writeValueAsString(member);
        redisTemplate.opsForList().leftPush(email,mappperv);
    }

    public Long waitingUserCountByRedis(String key) {
        var list = redisTemplate.opsForList();
        return list.size(key);
    }
    public List<RequestMatching> getMatchingMemberByRedis(String key, Long matchingQuota, Class<RequestMatching> count) {
        ObjectMapper mapper = new ObjectMapper();
        List<RequestMatching> resualtlist = new ArrayList<>();
        try{
            List<Object> jsonList =  redisTemplate.opsForList().rightPop(key, matchingQuota);
            for (int i = 0; i < jsonList.size(); i++) {
                resualtlist.add(mapper.readValue((String) jsonList.get(i), count));}
        } catch (Exception e){
            throw new NotFoundException.NotFoundMemberException();
        }
        return resualtlist;
    }
    public void matchingCancelByRedis(RequestMatching member) {
        try{
            var val = objectMapper.writeValueAsString(member);
            redisTemplate.opsForList().remove(member.getKey(),1,val);
        }catch (Exception e){
            log.info( " = error Massege {} = " , e.getMessage());
        }
    }


    public void addRefreshTokenByRedis(String email, String refreshToken, Duration duration) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(email, refreshToken, duration);
        log.info("Redis email : " + email);
        log.info("Redis refreshToken : " + stringValueOperations.get(refreshToken));
    }

    public void logoutAccessTokenByRedis(String accessToken, String logout, Long expiretime, TimeUnit milliseconds) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(accessToken, logout, expiretime,milliseconds);
        log.info("Redis logout : " + accessToken);
    }
    public void deleteRefreshTokenByRedis(String email){
        stringRedisTemplate.delete(email);
    }


}
