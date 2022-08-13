package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ChatMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "chatMemberId")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatRoom_id")
    private ChatRoom chatRoom;

    private String roomName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 방에서 나간 최근 시간
    @UpdateTimestamp
    private Timestamp recentOutTime;

    // 방 현재 접속 여부
    private Boolean present_position; // true : 접속 중 , false : 접속 X

    private int lastReadPoint;

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        chatRoom.getChatMemberList().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getChatMemberList().add(this);
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setPresent_position(Boolean present_position) {
        this.present_position = present_position;
    }

    public void setLastReadPoint(int lastReadPoint) {
        this.lastReadPoint = lastReadPoint;
    }
}
