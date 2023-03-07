package com.nbcamp.gamematching.matchingservice.matching.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.config.StompSessionInterceptor;
import com.nbcamp.gamematching.matchingservice.discord.service.DiscordService;
import com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto.MatchingResultQueryDto;
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException.NotFoundMatchingException;
import com.nbcamp.gamematching.matchingservice.matching.dto.NicknameDto;
import com.nbcamp.gamematching.matchingservice.matching.dto.RequestMatching;
import com.nbcamp.gamematching.matchingservice.matching.dto.ResponseUrlInfo;
import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
import com.nbcamp.gamematching.matchingservice.matching.repository.MatchingLogRepository;
import com.nbcamp.gamematching.matchingservice.matching.repository.ResultMatchingRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.service.MemberService;
import com.nbcamp.gamematching.matchingservice.redis.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final DiscordService discordService;
    private final ResultMatchingRepository resultMatchingRepository;
    private final MatchingLogRepository matchingLogRepository;
    private final MemberService memberService;
    private final RedisService redisService;


    @Transactional
    public ResponseUrlInfo matchingJoin(RequestMatching request, HttpServletRequest servletRequest)
            throws JsonProcessingException {
        Long matchingQuota = Long.valueOf(request.getMemberNumbers());

        //방 현재 인원 체크
        var matchingRoomCapacity = redisService.waitingUserCountAndRedisConnectByRedis(request.getKey());
        log.info(" 현재 방 입장 인원 =={ }==",matchingRoomCapacity.toString());
        if (matchingRoomCapacity < matchingQuota - 1) {

            redisService.machedEnterByRedis(request.getKey(), request);
            var topicName = "";
            var topicNameSelector
                    = redisService.findByFirstJoinUserByRedis(request.getKey(), RequestMatching.class);
            topicName = topicNameSelector.getMemberEmail();
            return  new ResponseUrlInfo(request,topicName);
        }

        //매칭 정원이 찻을 경우
        redisService.machedEnterByRedis(request.getKey(), request);
        var resultMemberList =
                redisService.getMatchingMemberByRedis(request.getKey(), matchingQuota, RequestMatching.class);

        List<Member> members = resultMemberList.stream()
                .map(o -> memberService.responseMemberByMemberEmail(o.getMemberEmail())).toList();

        Optional<String> resultUrl = discordService.createChannel(resultMemberList.get(0).getGameMode(),
                Integer.parseInt(resultMemberList.get(0).getMemberNumbers()));

        String url = "";
        if (resultUrl.isPresent()) {
            url = resultUrl.get();
        } else {
            throw new IllegalArgumentException("url을 찾을 수 없습니다.");
        }
        var topicName = resultMemberList.get(0).getMemberEmail();


        for (int i = 0; i < resultMemberList.size(); i++) {
            var resultMember = members.get(i);
            var resultMatching = ResultMatching.builder()
                    .gameInfo(resultMemberList.get(i).getKey())
                    .playMode(resultMemberList.get(i).getGameMode())
                    .discordUrl(url)
                    .build();
            resultMatchingRepository.saveAndFlush(resultMatching);
            MatchingLog matchingLog = new MatchingLog(resultMatching, resultMember);
            matchingLogRepository.saveAndFlush(matchingLog);
            matchingLog.setMember(resultMember); // 연관관계 편의 메소드때문에 필요해요! 기존 로직을 건드리진 않습니다!
        }
        var currentmatchingId= resultMatchingRepository.findFirstByDiscordUrl(url)
                    .orElseThrow(NotFoundMatchingException::new);
        return ResponseUrlInfo.builder()
                .matchingId(currentmatchingId.getId())
                .member(request)
                .topicName(topicName)
                .url(url).build();
    }
    public Optional<List<MatchingResultQueryDto>> findByMatchingResultMemberNicknameByMemberId(Long id) {
        return matchingLogRepository.findByMatchingResultMemberNicknameByMemberId(id);
    }

    @Override
    public List<NicknameDto> findMatchingMembers(Long matchingId, Long memberId) {
        ResultMatching resultMatching = resultMatchingRepository.findById(matchingId)
                .orElseThrow(NotFoundMatchingException::new);
        List<MatchingLog> matchingLogs = matchingLogRepository.findAllByResultMatching(
                resultMatching);
        return memberService.findNicknamesInMatching(matchingLogs, memberId);
    }

    @Override
    public ResultMatching findResultMatchingById(Long matchingId) {
        return resultMatchingRepository.findById(matchingId)
                .orElseThrow(NotFoundMatchingException::new);
    }

}









