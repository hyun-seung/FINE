package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private boolean soloCheck; // true -> 1:1 / false -> 1:N

    @UpdateTimestamp
    private Timestamp updateTime;

    @Column(columnDefinition = "VARCHAR(100)")
    private String latestMessage;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMember> chatMemberList = new ArrayList<>();

    public void setLatestMessage(String latestMessage) {
        if(latestMessage.strip().length() > 100) {
            this.latestMessage = latestMessage.strip().substring(0, 100);
        } else {
            this.latestMessage = latestMessage.strip();
        }
    }
}
