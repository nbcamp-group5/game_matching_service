package com.nbcamp.gamematching.matchingservice.chat.controller;

import com.nbcamp.gamematching.matchingservice.chat.dto.ChatRoomDto;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatMessage;
import com.nbcamp.gamematching.matchingservice.chat.entity.ChatRoom;
import com.nbcamp.gamematching.matchingservice.chat.repository.ChatRoomRepository;
import com.nbcamp.gamematching.matchingservice.chat.service.ChatRoomServiceImpl;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomServiceImpl chatRoomService;
    private final MemberRepository memberRepository;


    //채팅 페이지
    @GetMapping("/room")
    public String rooms(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "/chat/chat";
    }

    @GetMapping("/member")
    @ResponseBody
    public List<Member> members() {
        return memberRepository.findAll();
    }

    //내 채팅룸 가져오기 => 수정해야할 수 있음
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> myChatRooms(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatRoomService.myChatRooms(userDetails.getUser().getEmail());
    }

    //하나의 채팅방 정보 가져오기 - 채팅방 리스트에서 클릭하면 id 가져와서
    @GetMapping("/room/enter/{friendNick}")
    @ResponseBody
    public ChatRoomDto getChatRoom(@PathVariable String friendNick, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatRoomService.getChatRoom(friendNick, userDetails.getMember());
    }

    //이전의 채팅 기록 가져오기
    @GetMapping("/room/{roomId}/message")
    @ResponseBody
    public ResponseEntity<List<ChatMessage>> getChatMessage(@PathVariable Long roomId) {
        List<ChatMessage> chatMessageList = chatRoomService.getChatMessage(roomId);
        return ResponseEntity.ok().body(chatMessageList);
    }


}