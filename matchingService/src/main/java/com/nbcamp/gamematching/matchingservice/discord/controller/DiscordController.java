package com.nbcamp.gamematching.matchingservice.discord.controller;

import com.nbcamp.gamematching.matchingservice.discord.dto.DiscordRequest;
import com.nbcamp.gamematching.matchingservice.discord.service.DiscordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/discord")
@RequiredArgsConstructor
public class DiscordController {

    private final DiscordService discordService;

    @PostMapping("/test2")
    public void create(
            @PathVariable String discordname
    ) {
        List<String> list = new ArrayList<>();
//        if(!vaildDiscordId(discordname)){
//            throw new IllegalArgumentException();
//        }
        list.add(discordname);
        int limitPlayer = 5;
        discordService.createChannel("ㅈㄱㅃㄱ", list,limitPlayer);
    }

    @PostMapping("/check") //        /api/discord/check
    public boolean vaildDiscordId(@RequestBody DiscordRequest discordRequest) {
        return discordService.userCheck(discordRequest);
    }

    @DeleteMapping("/test")
    public void delete() {
        discordService.deleteChannel();
    }
}
