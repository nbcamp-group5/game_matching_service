package com.nbcamp.gamematching.matchingservice.comment.repository.query;

import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.nbcamp.gamematching.matchingservice.comment.entity.QComment.comment;

@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CommentResponse> findComments(Long boardId, Pageable pageable) {

        List<CommentResponse> content = jpaQueryFactory
                .select(Projections.constructor(CommentResponse.class,
                        comment.id,
                        comment.member.profile.nickname,
                        comment.member.profile.profileImage,
                        comment.content,
                        comment.createdAt,
                        comment.modifiedAt
                        ))
                .from(comment)
                .where(
                        comment.board.id.eq(boardId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(comment.count())
                .from(comment)
                .where(
                        comment.board.id.eq(boardId)
                );


        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Override
    public List<CommentResponse> findComments(Long boardId) {

        return jpaQueryFactory
                .select(Projections.constructor(CommentResponse.class,
                        comment.id,
                        comment.member.profile.nickname,
                        comment.member.profile.profileImage,
                        comment.content,
                        comment.createdAt,
                        comment.modifiedAt
                ))
                .from(comment)
                .where(
                        comment.board.id.eq(boardId)
                )
                .fetch();
    }
}
