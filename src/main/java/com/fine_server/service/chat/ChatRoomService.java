package com.fine_server.service.chat;

import com.fine_server.entity.ChatRoom;
import com.fine_server.entity.Member;
import com.fine_server.entity.RoomCollection;
import com.fine_server.entity.chat.CreateGroupChatRoomDto;
import com.fine_server.entity.chat.CreateSoloChatRoomDto;
import com.fine_server.entity.chat.RoomCollectionDto;
import com.fine_server.repository.ChatRoomRepository;
import com.fine_server.repository.MemberRepository;
import com.fine_server.repository.RoomCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private final MemberRepository memberRepository;

    private final RoomCollectionRepository roomCollectionRepository;

    public ChatRoom getChatRoom(Long roomId) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        ChatRoom chatRoom = optionalChatRoom.get();
        return chatRoom;
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

        RoomCollection memberInfo = new RoomCollectionDto(chatRoom, member).toEntity();
        RoomCollection receiverInfo = new RoomCollectionDto(chatRoom, receiver).toEntity();

        memberInfo.setRoomName(receiver.getNickname());
        receiverInfo.setRoomName(member.getNickname());

        roomCollectionRepository.save(memberInfo);
        roomCollectionRepository.save(receiverInfo);
        chatRoomRepository.save(chatRoom);

        chatRoom.getRoomCollectionList().add(memberInfo);
        chatRoom.getRoomCollectionList().add(receiverInfo);

        return chatRoom;
    }

    public ChatRoom createGroupChatRoom(CreateGroupChatRoomDto groupChatRoomDto) {
        ChatRoom chatRoom = groupChatRoomDto.toEntity();

        Optional<Member> optionalMember = memberRepository.findById(groupChatRoomDto.getMyId());
        Member member = optionalMember.get();

        RoomCollection memberInfo = new RoomCollectionDto(chatRoom, member).toEntity();

        memberInfo.setRoomName(groupChatRoomDto.getRoomName());
        chatRoom.getRoomCollectionList().add(memberInfo);
        roomCollectionRepository.save(memberInfo);

        for(Long receiverId : groupChatRoomDto.getReceiverList()) {
            Optional<Member> optionalReceiver = memberRepository.findById(receiverId);
            Member receiver = optionalReceiver.get();

            RoomCollection receiverInfo = new RoomCollectionDto(chatRoom, receiver).toEntity();

            receiverInfo.setRoomName(groupChatRoomDto.getRoomName());
            chatRoom.getRoomCollectionList().add(receiverInfo);
            roomCollectionRepository.save(receiverInfo);
        }

        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }
}
