package com.nbcamp.gamematching.matchingservice.discord.controller;

//import com.example.board.exception.NotFoundException;
import com.nbcamp.gamematching.matchingservice.discord.service.DiscordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/discord")
@RequiredArgsConstructor
public class DiscordController {
    private final DiscordService discordService;

    @PostMapping("/test2")
    public void create(
            @PathVariable String discordname
    ) {
        List<String> list = new ArrayList<>();
        if(!userCheck(discordname)){
            throw new IllegalArgumentException();
        }
        list.add(discordname);
        discordService.createChannel("ㅈㄱㅃㄱ", list);
    }

    @GetMapping("/check")
    public Boolean userCheck(String userTag) {
        return discordService.userCheck(userTag);
    }

    @DeleteMapping("/test")
    public void delete() {
        discordService.deleteChannel();
    }
}
