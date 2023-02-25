package com.nbcamp.gamematching.matchingservice.matching.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.chat.dto.ResponseMatch;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final DiscordService discordService;
    private final MatchingLogRepository matchingLogRepository;
    private final MemberService memberService;
    private final RedisService redisService;


    public ResponseMatch joinMatchingRoom(RequestMatching request, HttpServletRequest servletRequest) throws JsonProcessingException {
        Long matchingQuota = Long.valueOf(request.getMemberNumbers());
        //캔슬을 위한 세션 대기
//        var session = servletRequest.getSession();
//        session.setAttribute("UserSession", request);
//        session.setMaxInactiveInterval(10 * 60);
        if (redisService.waitingUserCountByRedis(request.getKey()) < matchingQuota-1) {
            redisService.machedEnterByRedis(request.getKey(), request);
        var topicName = "";
        var topicNameSelector
                = redisService.findByFirstJoinUserByRedis(request.getKey(), RequestMatching.class);
        topicName = topicNameSelector.getMemberEmail();
        System.out.println("current waitingUserCount = "
                + redisService.waitingUserCountByRedis(request.getKey()));
        return new ResponseMatch(topicName);
        }



        //매칭 정원이 찻을 경우
        var resualtMemberList =
                redisService.getMatchingMemberByRedis(request.getKey(), matchingQuota, RequestMatching.class);

//            redisService.enterSuccessMatchingRoom(topicName,request.getMemberEmail());

        // 매칭 정원이 다 찻을경우 새로운 곳으로 옮기기 ?
        // 현재 토픽에있는 전원에게 보낼경우 6번째 멤버도 혹시나 받지 않을까 ?

        List<String> discordIdList = new ArrayList<>();
        for (RequestMatching member : resualtMemberList) {
            discordIdList.add(member.getDicordName());
        }
//        resualtMemberList.stream().map(o -> memberService.responseMemberByMemberEmail(o.getMemberEmail())).toList();

        List<Member> members = new ArrayList<>();
        for (RequestMatching requestMatching : resualtMemberList) {
            members.add(memberService.responseMemberByMemberEmail(requestMatching.getMemberEmail()));
        }


        var resultUrl = discordService.createChannel(resualtMemberList.get(0).getGameMode(),
                discordIdList, Integer.parseInt(resualtMemberList.get(0).getMemberNumbers()));
        String url = "";
        if (resultUrl.isPresent()) {
            System.out.println(url);
            url = resultUrl.get();
        } else {
            throw new IllegalArgumentException("오류");
        }
        var topicName = resualtMemberList.get(0).getMemberEmail();
        var responseMatching = MatchingLog.builder()
                .gameName(resualtMemberList.get(0).getGameName())
                .playMode(resualtMemberList.get(0).getGameMode())
                .discordUrl(url)
                .matchingMemberList(members)
                .build();
//
//        MatchingLog response = new MatchingLog(resualtMemberList.get(0).getGameMode(),
//                resualtMemberList.get(0).getGameName(), url,members);

        matchingLogRepository.save(responseMatching);

        return new ResponseMatch(topicName,url);
    }
}




    //매칭 버튼 누르면     //매칭중엔 소켓연결 시작해서 세션관리 시작
    //메세지 핸들러  구독은 프론트에서 유저가 직접 들어오게 유저를 유도

    //매칭중 페이지로 이동 <웹소켓 세션시작> (매칭시작)>





    // (매칭취소/이탈)  레디스 목록에서 삭제 메소드 호출 : 매칭 준비 페이지 이동
    //정원차면<레디스 벨류 제거> 각 인원에게 url 전달

    //y 하면 다른사람 누르는거 대기 // (조건) 전체 y 누르면 해당 멤버들 리스트 받아서 url 만들고 뿌림
    //n 하면 > 매칭 준비 페이지로 이동
    //매칭이 완료되면 > 그 멤버들의 아이디를 받아 url호출

    //매칭버튼을 누른다 > 입장 구독시작
    //매칭중이 뜬다 >
    //매칭완료가 된다 > 완료와 동시에 팝 > 수락을 누르면 다시 구독 ?








