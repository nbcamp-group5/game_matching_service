package com.nbcamp.gamematching.matchingservice.chat.entity;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String roomName;


    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member user1;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member user2;

    static public ChatRoom create(Member user1, Member user2) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomName = createRoomName(user1.getProfile().getNickname(), user2.getProfile().getNickname());
        chatRoom.user1 = user1;
        chatRoom.user2 = user2;
        return chatRoom;
    }

    static public String createRoomName(String name1, String name2) {
        String[] names = {name1, name2};
        Arrays.sort(names);
        return names[0] + "-" + names[1];
    }


}
