package com.fine_server.entity.chat;

import com.fine_server.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {

    private MessageType type;

    private Long roomId;

    private Long memberId;

    private String message;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .message(message)
                .build();
    }

}
