package com.fine_server.entity.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetChatRoomDto {

    private Long memberId;

    private Long roomId;
}
