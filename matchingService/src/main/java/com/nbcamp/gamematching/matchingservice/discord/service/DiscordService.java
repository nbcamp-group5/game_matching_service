package com.nbcamp.gamematching.matchingservice.discord.service;


import com.nbcamp.gamematching.matchingservice.discord.dto.DiscordRequest;

import java.util.List;
import java.util.Optional;

public interface DiscordService {
    Optional<String> createChannel (String category, List<String> userTagList,int limitPlayer);
    void deleteChannel();
    boolean userCheck(DiscordRequest discordRequest);
}
