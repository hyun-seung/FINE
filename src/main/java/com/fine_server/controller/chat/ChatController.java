package com.fine_server.controller.chat;

import com.fine_server.entity.Member;
import com.fine_server.entity.chat.ChatMessageDto;
import com.fine_server.entity.chat.MessageType;
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
        // 입장 시
        if(MessageType.ENTER.equals(message.getType())) {
            Member member = chatMessageService.findMember(message.getMemberId());
            message.setMessage(member.getNickname() + "님이 입장하셨습니다.");
        }

        messageingTemplate.convertAndSend("/sub/message/" + message.getRoomId(), message);
        chatMessageService.makeChatMessage(message);
    }
}
