package com.nbcamp.gamematching.matchingservice.chat.dev;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Msg {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String content;
//    private Long senderId;
//    private Long targetId;
//    private Long subscriberId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn
//    private ChatRoom chatRoom;
//    @Enumerated(EnumType.STRING)
//    private MessageType type;
//    private boolean isRead;
//
//    // 방 id , 보낸사람 id , 메세지
//
//    @Builder
//    public Msg(ChatRoom chatRoom, String content, Long senderId, Long targetId, Long subscriberId, MessageType type) {
//        this.targetId = targetId;
//        this.content = content;
//        this.senderId = senderId;
//        this.subscriberId = subscriberId;
//        this.chatRoom = chatRoom;
//        this.type = type;
//        this.isRead = false;
//    }
//
//    public void read() {
//        this.isRead = true;
//    }
//}
private static final long serialVersionUID = 2082503192322391880L;

    private String roomId;

    private String message;

}