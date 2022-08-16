package com.fine_server.entity.chat;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class GetMemberChatRoomDto {

    private Long roomId;

    private int imageNum;

    private String roomName;

    private boolean soloCheck;

    private int memberCount;

    private String latestMessage;

    private Timestamp lastMessageTime;

    private int unreadMessageCount;

    public GetMemberChatRoomDto(Long roomId, int imageNum, String roomName, boolean soloCheck, int memberCount,
                                String latestMessage, Timestamp lastMessageTime, int unreadMessageCount) {
        this.roomId = roomId;
        this.imageNum = imageNum;
        this.roomName = roomName;
        this.soloCheck = soloCheck;
        this.memberCount = memberCount;

        this.latestMessage = latestMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadMessageCount = unreadMessageCount;
    }
}
