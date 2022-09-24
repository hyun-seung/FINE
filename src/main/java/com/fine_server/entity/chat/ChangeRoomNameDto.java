package com.fine_server.entity.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRoomNameDto {

    Long memberId;

    Long roomId;

    String roomName;

    int roomImageNum;
}
