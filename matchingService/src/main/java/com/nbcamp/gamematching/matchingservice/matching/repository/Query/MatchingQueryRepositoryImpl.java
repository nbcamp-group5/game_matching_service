package com.nbcamp.gamematching.matchingservice.matching.repository.Query;

import com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto.MatchingResultQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nbcamp.gamematching.matchingservice.matching.entity.QMatchingLog.matchingLog;
import static com.nbcamp.gamematching.matchingservice.matching.entity.QResultMatching.resultMatching;
import static com.nbcamp.gamematching.matchingservice.member.entity.QMember.member;

@RequiredArgsConstructor
public class MatchingQueryRepositoryImpl implements MatchingQueryRepository {
    private final JPAQueryFactory queryFactory;
    @Override
    @Transactional
    public Optional<List<MatchingResultQueryDto>> findByMatchingResultMemberNicknameByMemberId(Long memberId) {
            var matching =
                    Optional.ofNullable(queryFactory.select(Projections.constructor(
                                    MatchingResultQueryDto.class,
                                    matchingLog.resultMatching.id,
                                    matchingLog.resultMatching.playMode,
                                    matchingLog.resultMatching.gameInfo,
                                    matchingLog.resultMatching.discordUrl
                            ))
                            .from(matchingLog)
                            .where(matchingLog.member.Id.eq(memberId))
                            .fetch());

            if (matching.isPresent()){
                List<MatchingResultQueryDto> matchingResultList = new ArrayList<>();
                for (MatchingResultQueryDto currentM : matching.get()) {
                    Long matchingId = currentM.getResultId();
                    List<String> membersNickname = queryFactory.select(member.profile.nickname).from(member)
                            .join(matchingLog).on(member.Id.eq(matchingLog.member.Id))
                            .join(matchingLog.resultMatching,resultMatching)
                            .where(resultMatching.id.eq(matchingId))
                            .fetch();
                    currentM.addMember(membersNickname);
                    matchingResultList.add(currentM);
                }
                return Optional.of(matchingResultList);
            } else return Optional.empty();
        }
    }
