package com.nbcamp.gamematching.matchingservice.discord.controller;

import com.nbcamp.gamematching.matchingservice.discord.service.DiscordService;
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (!userCheck(discordname)) {
            throw new NotFoundException.NotFoundMemberException();
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
