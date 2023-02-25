package com.nbcamp.gamematching.matchingservice.discord.service;

import com.nbcamp.gamematching.matchingservice.config.DiscordJdaConfig;
import com.nbcamp.gamematching.matchingservice.discord.dto.DiscordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
public class DiscordServiceImpl implements DiscordService {
    private final DiscordJdaConfig jdaConfig;

    @Transactional
    public Optional<String> createChannel(String category, List<String> discordIdList, int matchingQuota) {
        Optional<String> url = Optional.ofNullable(null);

        try {
            url = Optional.ofNullable(jdaConfig.createVoiceChannel(category, discordIdList, matchingQuota));
        } catch (ExecutionException | InterruptedException | NullPointerException e) {
            e.printStackTrace();
        }
        return url;
    }

    // 매일 자정 12시마다 비어있는 방을 지운다.
    @Transactional
//    @Scheduled(cron = "0 0 0 * * *")
    public void deleteChannel() {
        jdaConfig.deleteVoiceChannel();
    }

    @Transactional
    public boolean userCheck(DiscordRequest discordRequest) {
        if (jdaConfig.checkMember(discordRequest)) {
            return true;
        }else throw new IllegalArgumentException("아이디없음");
    }
}