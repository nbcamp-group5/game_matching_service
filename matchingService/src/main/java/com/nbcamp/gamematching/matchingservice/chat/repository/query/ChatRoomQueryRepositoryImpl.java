package com.nbcamp.gamematching.matchingservice.chat.repository.query;

import com.nbcamp.gamematching.matchingservice.chat.dto.ChatRoomDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.nbcamp.gamematching.matchingservice.chat.entity.QChatRoom.chatRoom;

@RequiredArgsConstructor
public class ChatRoomQueryRepositoryImpl implements ChatRoomQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ChatRoomDto findChatRoom(String roomName) {

        return jpaQueryFactory
                .select(Projections.constructor(ChatRoomDto.class,
                        chatRoom.id,
                        chatRoom.roomName,
                        chatRoom.user1.profile.nickname,
                        chatRoom.user2.profile.nickname
                ))
                .from(chatRoom)
                .where(
                        chatRoom.roomName.eq(roomName)
                )
                .fetchOne();
    }

}
