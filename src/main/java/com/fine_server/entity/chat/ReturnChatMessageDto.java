package com.fine_server.entity.chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReturnChatMessageDto {

    private MessageType type;

    private Long roomId;

    private Long memberId;

    private String nickName;

    private String message;

    private int unreadCount;

    private LocalDateTime createdTime;

    public ReturnChatMessageDto(MessageType type, Long roomId,
                                Long memberId, String nickName, String message,
                                int unreadCount, LocalDateTime createdTime) {
        this.type = type;
        this.roomId = roomId;
        this.memberId = memberId;
        this.nickName = nickName;
        this.message = message;
        this.unreadCount = unreadCount;
        this.createdTime = createdTime;
    }
}
