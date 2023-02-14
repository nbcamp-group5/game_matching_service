package com.nbcamp.gamematching.matchingservice.chat.controller;

import com.nbcamp.gamematching.matchingservice.chat.entity.ChatRoom;
import com.nbcamp.gamematching.matchingservice.chat.repository.ChatRoomRepository;
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticatedPrincipal;
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
    private final JwtUtil jwtUtil;


    //채팅 페이지
    @GetMapping("/room")
    public String rooms() {
        return "/chat";
    }

    //내 채팅룸 가져오기 => 수정해야할 수 있음
    @GetMapping("/rooms/{nickname}")
    @ResponseBody
    public List<ChatRoom> myChatRooms(@PathVariable String nickname) {
        List<ChatRoom> chatRooms = chatRoomRepository.findMyChat(nickname);
        return chatRooms;
    }

    //채팅룸 개설
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String sender, String receiver) {
        return chatRoomRepository.createChatRoom(sender, receiver);
    }

    //하나의 채팅방 들어가기
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    //하나의 채팅방 정보 가져오기 - 채팅방 리스트에서 클릭하면 id 가져와서
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }


}