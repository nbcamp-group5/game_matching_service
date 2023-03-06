package com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto.QMatchingResultQueryDto is a Querydsl Projection type for MatchingResultQueryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMatchingResultQueryDto extends ConstructorExpression<MatchingResultQueryDto> {

    private static final long serialVersionUID = 1945853169L;

    public QMatchingResultQueryDto(com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> url, com.querydsl.core.types.Expression<String> gameMode, com.querydsl.core.types.Expression<String> gameInfo, com.querydsl.core.types.Expression<? extends java.util.List<Long>> memberNumbers, com.querydsl.core.types.Expression<String> key) {
        super(MatchingResultQueryDto.class, new Class<?>[]{String.class, String.class, String.class, String.class, java.util.List.class, String.class}, email, url, gameMode, gameInfo, memberNumbers, key);
    }

}

