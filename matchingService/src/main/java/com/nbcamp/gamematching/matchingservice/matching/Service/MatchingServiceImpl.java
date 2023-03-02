package com.nbcamp.gamematching.matchingservice.matching.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.discord.service.DiscordService;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseUrlInfo;
import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.matching.repository.MatchingLogRepository;
import com.nbcamp.gamematching.matchingservice.matching.repository.ResponseMatchingRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.service.MemberService;
import com.nbcamp.gamematching.matchingservice.redis.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final DiscordService discordService;
    private final ResponseMatchingRepository responseMatchingRepository;
    private final MatchingLogRepository matchingLogRepository;
    private final MemberService memberService;
    private final RedisService redisService;


    public ResponseUrlInfo joinMatchingRoom(RequestMatching request,
            HttpServletRequest servletRequest) throws JsonProcessingException {
        Long matchingQuota = Long.valueOf(request.getMemberNumbers());
        if (redisService.waitingUserCountByRedis(request.getKey()) < matchingQuota - 1) {
            redisService.machedEnterByRedis(request.getKey(), request);
            var topicName = "";
            var topicNameSelector
                    = redisService.findByFirstJoinUserByRedis(request.getKey(),
                    RequestMatching.class);
            topicName = topicNameSelector.getMemberEmail();
            return new ResponseUrlInfo(topicName);
        }
        //매칭 정원이 찻을 경우
        redisService.machedEnterByRedis(request.getKey(), request);
        var resultMemberList =
                redisService.getMatchingMemberByRedis(request.getKey(), matchingQuota,
                        RequestMatching.class);

        List<Member> members = resultMemberList.stream()
                .map(o -> memberService.responseMemberByMemberEmail(o.getMemberEmail())).toList();

        Optional<String> resultUrl = discordService.createChannel(
                resultMemberList.get(0).getGameMode(),
                Integer.parseInt(resultMemberList.get(0).getMemberNumbers()));
        String url = "";
        if (resultUrl.isPresent()) {
            url = resultUrl.get();
        } else {
            throw new IllegalArgumentException("url을 찾을 수 없습니다.");
        }
        var topicName = resultMemberList.get(0).getMemberEmail();
        var responseMatching = com.nbcamp.gamematching.matchingservice.matching.entity.ResponseMatching.builder()
                .gameName(resultMemberList.get(0).getGameName())
                .playMode(resultMemberList.get(0).getGameMode())
                .discordUrl(url)
                .build();
        responseMatchingRepository.save(responseMatching);

        for (Member member : members) { //TODO: 바꿔달라고 요청해보기
            MatchingLog matchingLog = new MatchingLog(responseMatching, member);
            matchingLogRepository.save(matchingLog);
            matchingLog.setMember(member);
        }

        return new ResponseUrlInfo(topicName, url);
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








