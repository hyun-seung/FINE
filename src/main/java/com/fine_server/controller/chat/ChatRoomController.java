package com.fine_server.controller.chat;

import com.fine_server.entity.ChatMember;
import com.fine_server.entity.ChatRoom;
import com.fine_server.entity.chat.*;
import com.fine_server.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/room/{memberId}/{roomId}")
    public ReturnChatRoomDto getChatRoom(@PathVariable Long memberId, @PathVariable Long roomId) {
        ReturnChatRoomDto returnChatRoomDto = chatRoomService.getChatRoom(memberId, roomId);
        return returnChatRoomDto;
    }

    @GetMapping("/room/member/{roomId}")
    public ReturnChatRoomMemberDto getChatRoomMember(@PathVariable Long roomId) {
        ReturnChatRoomMemberDto returnChatRoomMemberDto = chatRoomService.getChatRoomMember(roomId);
        return returnChatRoomMemberDto;
    }

    // 필요 없어 보임..
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

    // 멤버가 속해 있는 chatRoomId 전체 조회
    @GetMapping("/rooms/login/{memberId}")
    public List<Long> getMemberChatRoomNumList(@PathVariable Long memberId) {
        List<Long> memberChatRoomNumList = chatRoomService.getMemberChatRoomNum(memberId);
        return memberChatRoomNumList;
    }


    @GetMapping("/rooms/chat/{memberId}")
    public List<GetMemberChatRoomDto> getMemberChatRoomList(@PathVariable Long memberId) {
        List<GetMemberChatRoomDto> memberChatRoomList = chatRoomService.getMemberChatRoom(memberId);
        return memberChatRoomList;
    }

    @PutMapping("/room/name")
    public ChatMember changeRoomName(@RequestBody ChangeRoomNameDto changeRoomNameDto) {
        ChatMember chatMember = chatRoomService.changeRoomNameAndImageNum(changeRoomNameDto);
        return chatMember;
    }

    @DeleteMapping("/room/{roomId}/{targetId}")
    public ChatRoom deleteMemberInChatRoom(@PathVariable Long roomId, @PathVariable Long targetId) {
        ChatRoom chatRoom = chatRoomService.quitChatRoom(roomId, targetId);
        return chatRoom;
    }
}
