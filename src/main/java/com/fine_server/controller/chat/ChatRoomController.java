package com.fine_server.controller.chat;

import com.fine_server.entity.ChatRoom;
import com.fine_server.entity.chat.CreateGroupChatRoomDto;
import com.fine_server.entity.chat.CreateSoloChatRoomDto;
import com.fine_server.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/room/{roomId}")
    public ChatRoom getChatRoom(@PathVariable Long roomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoom(roomId);
        return chatRoom;
    }

    @GetMapping("/rooms")
    public List<ChatRoom> getChatRooms() {
        List<ChatRoom> chatRoomList = chatRoomService.getChatRooms();
        return chatRoomList;
    }

    @PostMapping("/room/solo")
    @ResponseBody
    public ChatRoom creatSoloChatRoom(@RequestBody CreateSoloChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = chatRoomService.createSoloChatRoom(chatRoomDto);
        return chatRoom;
    }

    @PostMapping("/room/group")
    public ChatRoom creatGroupChatRoom(@RequestBody CreateGroupChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = chatRoomService.createGroupChatRoom(chatRoomDto);
        return chatRoom;
    }
}
