package com.nbcamp.gamematching.matchingservice.board.repository.query;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.nbcamp.gamematching.matchingservice.board.entity.QBoard.board;

@RequiredArgsConstructor
public class BoardQueryRepositoryImpl implements BoardQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Board> findAllBoard(Pageable pageable) {
        List<Board> content = jpaQueryFactory
                .select(board)
                .from(board)
                .leftJoin(board.member).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //전체 content 개수 count 쿼리 분리
        JPAQuery<Long> count = jpaQueryFactory
                .select(board.count())
                .from(board);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }


    //board는 dto로 받아오지 않은 이유
    //likecnt를 받아오는 로직이 service단에 따로 있음
    //service 로직까지 전부 수정해야하는 상황이 와서 일단 여긴 member 조인해서 board로 받으려고 함

    //여기서 또 조인을 사용해서 member를 다 넣는 이유는
    //service에서 boardresponse를 만들 때, member에서 꺼내오는 방법을 사용하기 때문에 어쩔 수 없이 member 전부 담아서 보냄
    @Override
    public Page<Board> findMyBoard(Long memberId, Pageable pageable) {

        List<Board> content = jpaQueryFactory
                .select(board)
                .from(board)
                .leftJoin(board.member).fetchJoin()
                .where(
                        board.member.Id.eq(memberId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //전체 content 개수 count 쿼리 분리
        JPAQuery<Long> count = jpaQueryFactory
                .select(board.count())
                .from(board)
                .where(
                        board.member.Id.eq(memberId)
                );

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);

        //size()의 경우, 단순히 list의 크기만 가져오는 것
        //이후 list가 아닌 type에서 쓸 수 없을지도, ,?
        //return PageableExecutionUtils.getPage(content, pageable, () -> (long) results.size());
    }
}