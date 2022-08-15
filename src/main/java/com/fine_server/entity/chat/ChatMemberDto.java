package com.fine_server.entity.chat;

import com.fine_server.entity.ChatMember;
import com.fine_server.entity.ChatRoom;
import com.fine_server.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMemberDto {

    private ChatRoom chatRoom;

    private Member member;

    public ChatMemberDto(ChatRoom chatRoom, Member member) {
        this.chatRoom = chatRoom;
        this.member = member;
    }

    public ChatMember toEntity() {

        ChatMember chatMember = ChatMember.builder().build();

        chatMember.setChatRoom(chatRoom);
        chatMember.setMember(member);
        chatMember.setPresentPosition(false);

        return chatMember;
    }
}
