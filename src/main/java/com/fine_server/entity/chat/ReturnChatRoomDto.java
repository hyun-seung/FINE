package com.fine_server.entity.chat;

import com.fine_server.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReturnChatRoomDto {

    private long roomId;

    private boolean soloCheck;

    private int memberCount;

    private String roomName;

    private List<SmallChatMessageDto> chatMessageList;

    public ReturnChatRoomDto(long roomId, boolean soloCheck, int memberCount,
                             String roomName, List<SmallChatMessageDto> chatMessageList) {
        this.roomId = roomId;
        this.soloCheck = soloCheck;
        this.memberCount = memberCount;
        this.roomName = roomName;
        this.chatMessageList = chatMessageList;
    }
}
