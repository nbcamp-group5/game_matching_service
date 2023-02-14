package com.nbcamp.gamematching.matchingservice.chat.repository;

import com.nbcamp.gamematching.matchingservice.chat.entity.ChatRoom;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@RequiredArgsConstructor
@Service
public class ChatRoomRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    private HashOperations<String, String, String> hashOpsEnterInfo;

    public static final String ENTER_INFO = "ENTER_INFO"; // 채팅룸에 입장한 클라이언트의 sessionId와 채팅룸 id를 맵핑한 정보 저장

    // Redis CacheKeys
    private static final String CHAT_ROOMS = "CHAT_ROOM"; // 채팅룸 저장


    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
    }

    //내 모든 채팅방 조회
    public List<ChatRoom> findMyChat(String nickname) {
        List<ChatRoom> chatRooms = opsHashChatRoom.values(CHAT_ROOMS)
                .stream()
                .filter(c -> c.getSender().equals(nickname) || c.getReceiver().equals(nickname))
                .collect(Collectors.toList());

        return chatRooms;
    }

    // 특정 채팅방 조회
    public ChatRoom findRoomById(String id) {
        return opsHashChatRoom.get(CHAT_ROOMS, id);
    }


    //채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
    public ChatRoom createChatRoom(String sender, String receiver) {
        ChatRoom chatRoom = ChatRoom.create(sender, receiver);
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    // 유저가 입장한 채팅방ID와 유저 세션ID 맵핑 정보 저장
    public void setUserEnterInfo(String sessionId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
    }

    // 유저 세션으로 입장해 있는 채팅방 ID 조회
    public String getUserEnterRoomId(String sessionId) {
        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
    }

    // 유저 세션정보와 맵핑된 채팅방ID 삭제
    public void removeUserEnterInfo(String sessionId) {
        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
    }

}
