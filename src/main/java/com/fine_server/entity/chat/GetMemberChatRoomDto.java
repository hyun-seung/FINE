package com.fine_server.entity.chat;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class GetMemberChatRoomDto {

    private Long roomId;

    private String roomName;

    private String latestMessage;

    private Timestamp lastMessageTime;

    private int unreadMessageCount;

    public GetMemberChatRoomDto(Long roomId, String roomName, String latestMessage, Timestamp lastMessageTime, int unreadMessageCount) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.latestMessage = latestMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadMessageCount = unreadMessageCount;
    }
}
