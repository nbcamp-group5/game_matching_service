package com.nbcamp.gamematching.matchingservice.chat.service;

import com.nbcamp.gamematching.matchingservice.chat.dto.ChatRoomDto;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatRoom;
import com.nbcamp.gamematching.matchingservice.chat.repository.ChatMessageRepository;
import com.nbcamp.gamematching.matchingservice.chat.repository.ChatRoomRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nbcamp.gamematching.matchingservice.chat.entity.ChatRoom.createRoomName;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final ChatMessageRepository chatMessageRepository;

    //내 채팅방 리스트 가져오기
    //채팅방이 없을 때의 로직 처리 추가하기
    @Transactional
    public List<ChatRoom> myChatRooms(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(IllegalAccessError::new);
        List<ChatRoom> myChatRooms = member.getChatRooms();
        return myChatRooms;
    }


    //채팅방 생성하기
    @Transactional
    public ChatRoomDto createChatRoom(Member member1, Member member2) {
        ChatRoom chatRoom = ChatRoom.create(member1, member2);
        chatRoomRepository.save(chatRoom);
        return new ChatRoomDto(chatRoom);
    }


    //선택한 채팅방 찾기
    //선택한 친구가 채팅방이 없으면
    @Transactional
    public ChatRoomDto getChatRoom(String friendNick, Member me) {

        Member friend = memberRepository.findByProfileNickname(friendNick);
        String roomName = createRoomName(friendNick, me.getProfile().getNickname());

        //채팅방이 있으면
        if (chatRoomRepository.existsByRoomName(roomName)) {
            return new ChatRoomDto(chatRoomRepository.findByRoomName(roomName));
        } else {
            return createChatRoom(friend, me);
        }
    }

    @Transactional
    public List<ChatMessage> getChatMessage(Long roomId) {
        return chatMessageRepository.findAllByRoomId(roomId);
    }


}
