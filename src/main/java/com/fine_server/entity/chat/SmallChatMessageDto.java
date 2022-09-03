package com.fine_server.entity.chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SmallChatMessageDto {

    private MemberSmallInfo memberInfo;

    private String message;

    private int unreadCount;

    private LocalDateTime createdTime;

    public SmallChatMessageDto(MemberSmallInfo memberInfo, String message, int unreadCount, LocalDateTime createdTime) {
        this.memberInfo = memberInfo;
        this.message = message;
        this.unreadCount = unreadCount;
        this.createdTime = createdTime;
    }
}
