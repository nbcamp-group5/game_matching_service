package com.nbcamp.gamematching.matchingservice.discord.controller;

import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import com.nbcamp.gamematching.matchingservice.discord.dto.DiscordRequest;
import com.nbcamp.gamematching.matchingservice.discord.service.DiscordService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/discord")
@RequiredArgsConstructor
public class DiscordController {

    private final DiscordService discordService;

    @PostMapping("/del")
    public void chanelDelete() {
        discordService.deleteChannel();
    }

}
