package com.nbcamp.gamematching.matchingservice.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException.NotFoundMemberException;
import com.nbcamp.gamematching.matchingservice.matching.Service.MatchingService;
import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
import com.nbcamp.gamematching.matchingservice.matching.repository.MatchingLogRepository;
import com.nbcamp.gamematching.matchingservice.member.dto.EvaluationOneMember;
import com.nbcamp.gamematching.matchingservice.member.dto.EvaluationRequest;
import com.nbcamp.gamematching.matchingservice.member.dto.MannerPointsRequest;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MannerPointsServiceImpl implements MannerPointsService {

    private final MatchingService matchingService;

    private final MemberRepository memberRepository;

    private final MatchingLogRepository matchingLogRepository; //TODO: 매칭을 실제 테스트하는 환경이 될때부터 멤버 엔티티에 있는 매칭로그를 활용할 예정. 그때에는 이 repository 주입을 지울 것

    @Override
    public ResponseEntity<String> changeMannerPointsByOne(EvaluationOneMember request, Long memberId) {
        ResultMatching resultMatching = matchingService.findResultMatchingById(request.getMatchingId());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        MatchingLog matchingLog = matchingLogRepository.findByResultMatchingAndMember(
                resultMatching, member);

        if (!matchingLog.getEvaluation()) {

            Member targetMember = memberRepository.findById(request.getTargetId())
                    .orElseThrow(NotFoundMemberException::new);

            targetMember.changeMannerPoints(request.getUpDown());
            matchingLog.changeEvaluation();

            return new ResponseEntity<>("평가가 완료되었습니다.", HttpStatus.OK);
        }

        return new ResponseEntity<>("이미 평가하였습니다.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changeMannerPoints(EvaluationRequest request, Long memberId)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<MannerPointsRequest> mannerPointsRequests = objectMapper.readValue(
                request.getRequests(), new TypeReference<List<MannerPointsRequest>>() {});

        ResultMatching resultMatching = matchingService.findResultMatchingById(request.getMatchingId());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        MatchingLog matchingLog = matchingLogRepository.findByResultMatchingAndMember(
                resultMatching, member);

        if (!matchingLog.getEvaluation()) {

            for (MannerPointsRequest mannerPointsRequest : mannerPointsRequests) {

                Member targetMember = memberRepository.findById(mannerPointsRequest.getTargetId())
                        .orElseThrow(NotFoundMemberException::new);

                targetMember.changeMannerPoints(mannerPointsRequest.getUpDown());
                matchingLog.changeEvaluation();
            }

            return new ResponseEntity<>("평가가 완료되었습니다.", HttpStatus.OK);
        }

        return new ResponseEntity<>("이미 평가하였습니다.", HttpStatus.OK);
    }

}
