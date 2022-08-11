package com.fine_server.controller.chat;

import com.fine_server.entity.chat.ChatMessageDto;
import com.fine_server.entity.chat.MessageType;
import com.fine_server.entity.chat.ReturnChatMessageDto;
import com.fine_server.service.chat.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messageingTemplate;

    private final ChatMessageService chatMessageService;

    @MessageMapping("/message")
    public void message(ChatMessageDto message) {

        // 입장 시(해당 방 입장 시)
        if(MessageType.ENTER.equals(message.getType())) {
            chatMessageService.enterRoom(message.getRoomId(), message.getMemberId());
        }

        // 채팅 시
        if(MessageType.TALK.equals(message.getType())) {
            ReturnChatMessageDto modifiedMessage = chatMessageService.makeChatMessage(message);
            messageingTemplate.convertAndSend("/sub/message/" + message.getRoomId(), modifiedMessage);
        }

        // 퇴장 시(해당 방 퇴장 시)
        if(MessageType.EXIT.equals(message.getType())) {
            chatMessageService.quitRoom(message.getRoomId(), message.getMemberId());
        }

    }
}
