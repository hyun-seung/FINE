package com.fine_server.service.chat;

import com.fine_server.entity.ChatMember;
import com.fine_server.entity.ChatRoom;
import com.fine_server.entity.Member;
import com.fine_server.entity.chat.*;
import com.fine_server.repository.ChatMemberRepository;
import com.fine_server.repository.ChatRoomRepository;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private final MemberRepository memberRepository;

    private final ChatMemberRepository chatMemberRepository;

    public ReturnChatRoomDto getChatRoom(Long memberId, Long roomId) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        ChatRoom chatRoom = optionalChatRoom.get();

        String roomName = " ";

        for(ChatMember chatMember : chatRoom.getChatMemberList()) {
            if(chatMember.getMember().getId().equals(memberId)) {
                roomName = chatMember.getRoomName();
            }
        }

        ReturnChatRoomDto returnChatRoomDto = new ReturnChatRoomDto(
                chatRoom.getRoomId(), chatRoom.isSoloCheck(), chatRoom.getChatMemberList().size(),
                roomName, chatRoom.getChatMessageList()
        );

        return returnChatRoomDto;
    }

    public ReturnChatRoomMemberDto getChatRoomMember(Long roomId) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        ChatRoom chatRoom = optionalChatRoom.get();

        ReturnChatRoomMemberDto returnChatRoomMemberDto = new ReturnChatRoomMemberDto(
                chatRoom.getRoomId(), chatRoom.getChatMemberList()
        );

        return returnChatRoomMemberDto;
    }

    public List<ChatRoom> getChatRooms() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        return chatRoomList;
    }

    public ChatRoom createSoloChatRoom(CreateSoloChatRoomDto soloChatRoomDto) {
        ChatRoom chatRoom = soloChatRoomDto.toEntity();

        Optional<Member> optionalMember = memberRepository.findById(soloChatRoomDto.getMyId());
        Member member = optionalMember.get();

        Optional<Member> optionalReceiver = memberRepository.findById(soloChatRoomDto.getReceiverId());
        Member receiver = optionalReceiver.get();

        ChatMember memberInfo = new ChatMemberDto(chatRoom, member).toEntity();
        ChatMember receiverInfo = new ChatMemberDto(chatRoom, receiver).toEntity();

        memberInfo.setRoomName(receiver.getNickname());
        memberInfo.setImageNum(receiver.getUserImageNum());
        receiverInfo.setRoomName(member.getNickname());
        receiverInfo.setImageNum(member.getUserImageNum());

        chatMemberRepository.save(memberInfo);
        chatMemberRepository.save(receiverInfo);
        chatRoomRepository.save(chatRoom);

        chatRoom.getChatMemberList().add(memberInfo);
        chatRoom.getChatMemberList().add(receiverInfo);

        return chatRoom;
    }

    public ChatRoom createGroupChatRoom(CreateGroupChatRoomDto groupChatRoomDto) {
        ChatRoom chatRoom = groupChatRoomDto.toEntity();

        Optional<Member> optionalMember = memberRepository.findById(groupChatRoomDto.getMyId());
        Member member = optionalMember.get();

        ChatMember memberInfo = new ChatMemberDto(chatRoom, member).toEntity();

        memberInfo.setRoomName(groupChatRoomDto.getRoomName());
        memberInfo.setImageNum(member.getUserImageNum());
        chatRoom.getChatMemberList().add(memberInfo);
        chatMemberRepository.save(memberInfo);

        for(Long receiverId : groupChatRoomDto.getReceiverList()) {
            Optional<Member> optionalReceiver = memberRepository.findById(receiverId);
            Member receiver = optionalReceiver.get();

            ChatMember receiverInfo = new ChatMemberDto(chatRoom, receiver).toEntity();

            receiverInfo.setRoomName(groupChatRoomDto.getRoomName());
            receiverInfo.setImageNum(member.getUserImageNum());
            chatRoom.getChatMemberList().add(receiverInfo);
            chatMemberRepository.save(receiverInfo);
        }

        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    // 멤버별 채팅방 조회 (채팅방 별 마지막 채팅시간 기준으로 정렬)
    public List<GetMemberChatRoomDto> getMemberChatRoom(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();

        List<ChatMember> chatMemberList = member.getChatMemberList();
        List<GetMemberChatRoomDto> returnList = new ArrayList<>();
        for(ChatMember chatMember : chatMemberList) {
            int lastReadPoint = chatMember.getLastReadPoint();
            int now = chatMember.getChatRoom().getChatMessageList().size();

            int unreadMessageCount = now - lastReadPoint;
            GetMemberChatRoomDto getMemberChatRoomDto = new GetMemberChatRoomDto(
                    chatMember.getChatRoom().getRoomId(), chatMember.getImageNum(), chatMember.getRoomName(),
                    chatMember.getChatRoom().isSoloCheck(), chatMember.getChatRoom().getChatMemberList().size(),
                    chatMember.getChatRoom().getLatestMessage(),
                    chatMember.getChatRoom().getUpdateTime(), unreadMessageCount
            );
            returnList.add(getMemberChatRoomDto);
        }

        Collections.sort(returnList, new Comparator<GetMemberChatRoomDto>() {
            @Override
            public int compare(GetMemberChatRoomDto o1, GetMemberChatRoomDto o2) {
                return o2.getLastMessageTime().compareTo(o1.getLastMessageTime());
            }
        });

        return returnList;
    }

    public List<Long> getMemberChatRoomNum(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();

        List<Long> roomNumList = new ArrayList<>();
        for(ChatMember chatMember : member.getChatMemberList()) {
            Long roomId = chatMember.getChatRoom().getRoomId();
            roomNumList.add(roomId);
        }

        return roomNumList;
    }

    public ChatMember changeRoomName(ChangeRoomNameDto changeRoomNameDto) {
        Optional<Member> optionalMember = memberRepository.findById(changeRoomNameDto.getMemberId());
        Member member = optionalMember.get();

        for(ChatMember chatMember : member.getChatMemberList()) {
            ChatRoom chatRoom = chatMember.getChatRoom();
            if(chatRoom.getRoomId().equals(changeRoomNameDto.getRoomId())) {
                chatMember.setRoomName(changeRoomNameDto.getRoomName());
                return chatMember;
            }
        }
        return null;
    }

}