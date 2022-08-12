package com.fine_server.entity.chat;

import com.fine_server.entity.ChatRoom;
import com.fine_server.entity.Member;
import com.fine_server.entity.RoomCollection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomCollectionDto {

    private ChatRoom chatRoom;

    private Member member;

    public RoomCollectionDto(ChatRoom chatRoom, Member member) {
        this.chatRoom = chatRoom;
        this.member = member;
    }

    public RoomCollection toEntity() {

        RoomCollection roomCollection = RoomCollection.builder().build();

        roomCollection.setChatRoom(chatRoom);
        roomCollection.setMember(member);

        return roomCollection;
    }
}
