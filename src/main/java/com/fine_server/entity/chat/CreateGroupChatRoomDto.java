package com.fine_server.entity.chat;

import com.fine_server.entity.ChatRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateGroupChatRoomDto {

    private Long myId;

    private List<Long> receiverList;

    private String roomName;

    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .soloCheck(false)
                .build();
    }
}
