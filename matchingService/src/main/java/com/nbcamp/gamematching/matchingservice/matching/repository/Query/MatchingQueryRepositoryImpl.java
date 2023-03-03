//package com.nbcamp.gamematching.matchingservice.matching.repository.Query;
//
//import com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto.MatchingResultQueryDto;
//import com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto.QMatchingResultQueryDto;
//import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
//import com.nbcamp.gamematching.matchingservice.matching.entity.QMatchingLog;
//import com.nbcamp.gamematching.matchingservice.matching.entity.QResultMatching;
//import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
//import com.nbcamp.gamematching.matchingservice.member.entity.Member;
//import com.nbcamp.gamematching.matchingservice.member.entity.QMember;
//import com.querydsl.core.Tuple;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//import static com.nbcamp.gamematching.matchingservice.matching.entity.QMatchingLog.matchingLog;
//import static com.nbcamp.gamematching.matchingservice.matching.entity.QResultMatching.*;
//import static com.nbcamp.gamematching.matchingservice.member.entity.QMember.member;
//
//@RequiredArgsConstructor
//public class MatchingQueryRepositoryImpl implements MatchingQueryRepository {
//
//    private final JPAQueryFactory queryFactory;
//
//
//    @Override
//    public MatchingLog findByResultMatchingAndMember() {
//        List<Tuple> matchingLogs = queryFactory
//                .select(member.Id,
//                        resultMatching.gameInfo,
//                        resultMatching.playMode)
//                .from(matchingLog)
//                .join(matchingLog.member,member)
//                .join(matchingLog.resultMatching,resultMatching)
//                .groupBy(matchingLog.id)
//                .fetch();
//
//                /**
//                 * 매칭완료된 것을 가져오고
//                 * 그 매칭완료된것에 들어있는 멤버들을 가져온다
//                 * 멤버들의 id값과 , 게임정보 , 게임모드
//                 */
//
//
//        return null;
//    }
//}
