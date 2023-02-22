package com.nbcamp.gamematching.matchingservice.matching.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.discord.service.DiscordService;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.matching.repository.MatchingLogRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.service.MemberService;
import com.nbcamp.gamematching.matchingservice.redis.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final DiscordService discordService;

    private final MatchingLogRepository matchingLogRepository;
    private final MemberService memberService;
    private final RedisService redisService;

    public void joinMatchingRoom(RequestMatching request, HttpServletRequest servletRequest) throws JsonProcessingException {
        Long matchingQuota= Long.valueOf(request.getMemberNumbers());
        redisService.machedEnterByRedis(request.getKey(),request);
        //캔슬을 위한 세션 대기
        var session = servletRequest.getSession();
        session.setAttribute("UserSession",request);
        session.setMaxInactiveInterval(10*60);
        System.out.println("waitingUserCount = " + redisService.waitingUserCountByRedis(request.getKey()));

        if(redisService.waitingUserCountByRedis(request.getKey()) < matchingQuota){

            return;
        }

        var resualtMemberList =
                redisService.getMatchingMemberByRedis(request.getKey(),matchingQuota,RequestMatching.class);

        List<Member> members =
                resualtMemberList.stream().map(o->memberService.responseMemberByMemberId(o.getMemberId())).toList();

        List<String> discordIdList = new ArrayList<>();
        for (RequestMatching member : resualtMemberList) {
            discordIdList.add(member.getDiscordId());
        }
        Optional<String> resultUrl = discordService.createChannel(request.getGameMode(),discordIdList,matchingQuota.intValue());

        String url = "";
        if (resultUrl.isPresent()) {
            System.out.println(url);
            url = resultUrl.get();
        }else{
            return;
        }

        var responseMatching = MatchingLog.builder()
                .gameName(request.getGameName())
                .playMode(request.getGameMode())
                .discordUrl(url)
                .matchingMemberList(members)
                .build();
        matchingLogRepository.save(responseMatching);


        for (int i = 0; i < resualtMemberList.size(); i++) {
            //각 멤버들에게 url을 날리는 메소드 호출
//            resualtMemberList

        }
    }

    //매칭이 성사되면 각 멤버들에게 url전송
    //조인컨ㅌ트롤러를 발동시켜서 각 유저에게 요청을 하지않고 응답을 받을수있게 바꿔준다.





}