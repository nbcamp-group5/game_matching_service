package com.nbcamp.gamematching.matchingservice.comment.repository.query;

import com.nbcamp.gamematching.matchingservice.comment.dto.AnonymousCommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.nbcamp.gamematching.matchingservice.comment.entity.QAnonymousComment.anonymousComment;

@RequiredArgsConstructor
public class AnonymousQueryRepositoryImpl implements AnonymousQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<AnonymousCommentResponse> findAnonymousComments(Long boardId, Pageable pageable) {

        List<AnonymousCommentResponse> content = jpaQueryFactory
                .select(Projections.constructor(AnonymousCommentResponse.class,
                        anonymousComment.id,
                        anonymousComment.nickname,
                        anonymousComment.content,
                        anonymousComment.createdAt,
                        anonymousComment.modifiedAt
                ))
                .from(anonymousComment)
                .where(
                        anonymousComment.anonymousBoard.id.eq(boardId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(anonymousComment.count())
                .from(anonymousComment)
                .where(
                        anonymousComment.anonymousBoard.id.eq(boardId)
                );


        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    public List<AnonymousCommentResponse> findAnonymousComments(Long boardId) {

        return  jpaQueryFactory
                .select(Projections.constructor(AnonymousCommentResponse.class,
                        anonymousComment.id,
                        anonymousComment.nickname,
                        anonymousComment.content,
                        anonymousComment.createdAt,
                        anonymousComment.modifiedAt
                ))
                .from(anonymousComment)
                .where(
                        anonymousComment.anonymousBoard.id.eq(boardId)
                )
                .fetch();
    }
}
