package com.fine_server.entity.chat;

import com.fine_server.entity.ChatMember;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReturnChatRoomMemberDto {

    private Long roomId;

    private List<ChatMember> chatMemberList;

    public ReturnChatRoomMemberDto(Long roomId, List<ChatMember> chatMemberList) {
        this.roomId = roomId;
        this.chatMemberList = chatMemberList;
    }
}
