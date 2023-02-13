package com.nbcamp.gamematching.matchingservice.discord.service;


import java.util.List;
import java.util.Optional;

public interface DiscordService {
    Optional<String> createChannel (String category, List<String> userTagList);
    void deleteChannel();
    boolean userCheck(String discordId);
}
