package com.fine_server.entity.chat;

import com.fine_server.entity.ChatRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSoloChatRoomDto {

    private Long myId;

    private Long receiverId;

    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .soloCheck(true)
                .build();
    }
}
