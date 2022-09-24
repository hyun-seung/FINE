package com.fine_server.service.chat;

import com.fine_server.entity.ChatMember;
import com.fine_server.entity.ChatMessage;
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

    private final ChatMessageService chatMessageService;

    // 입장할 때 실행 = unreadCount확인 후 list 보내기
    public ReturnChatRoomDto getChatRoom(Long memberId, Long roomId) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        ChatRoom chatRoom = optionalChatRoom.get();

        ChatMember myChatMember = null;

        for (ChatMember chatMember : chatRoom.getChatMemberList()) {
            if (chatMember.getMember().getId().equals(memberId)) {
                myChatMember = chatMember;
            }
        }

        chatMessageService.readMessage(chatRoom, myChatMember);

        List<SmallChatMessageDto> returnChatMessageDtoList = new ArrayList<>();
        for (ChatMessage chatMessage : chatRoom.getChatMessageList()) {
            SmallChatMessageDto smallChatMessageDto = new SmallChatMessageDto(
                    chatMessage.getSender().getMemberIdAndNicknameAndUserImageNumInfo(), chatMessage.getMessage(),
                    chatMessage.getUnreadCount(), chatMessage.getCreatedDate()
            );
            returnChatMessageDtoList.add(smallChatMessageDto);
        }

        ReturnChatRoomDto returnChatRoomDto = new ReturnChatRoomDto(
                chatRoom.getRoomId(), chatRoom.isSoloCheck(), chatRoom.getChatMemberList().size(),
                myChatMember.getRoomName(), returnChatMessageDtoList
        );

        return returnChatRoomDto;
    }

    public ReturnChatRoomMemberDto getChatRoomMember(Long roomId) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        ChatRoom chatRoom = optionalChatRoom.get();

        ReturnChatRoomMemberDto returnChatRoomMemberDto = new ReturnChatRoomMemberDto(
                chatRoom.getRoomId(), Long.parseLong(chatRoom.getMembers().split(",")[0]), chatRoom.getChatMemberList()
        );

        return returnChatRoomMemberDto;
    }

    public List<ChatRoom> getChatRooms() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        return chatRoomList;
    }

    public ChatRoom createSoloChatRoom(CreateSoloChatRoomDto soloChatRoomDto) {
        Long existSoloChatRoom = findExistSoloChatRoom(soloChatRoomDto.getMyId(), soloChatRoomDto.getReceiverId());
        if (existSoloChatRoom != null) {
            return chatRoomRepository.findById(existSoloChatRoom).get();
        }

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

        StringBuilder sb = new StringBuilder();
        String twoMembers = sb.append(member.getId()).append(",").append(receiver.getId()).toString();
        chatRoom.setMembers(twoMembers);

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
        memberInfo.setImageNum(groupChatRoomDto.getRoomImageNum());
        chatRoom.getChatMemberList().add(memberInfo);
        chatMemberRepository.save(memberInfo);

        StringBuilder sb = new StringBuilder();
        StringBuilder manyMemberBuilder = sb.append(member.getId());

        for (Long receiverId : groupChatRoomDto.getReceiverList()) {
            Optional<Member> optionalReceiver = memberRepository.findById(receiverId);
            Member receiver = optionalReceiver.get();

            ChatMember receiverInfo = new ChatMemberDto(chatRoom, receiver).toEntity();

            receiverInfo.setRoomName(groupChatRoomDto.getRoomName());
            receiverInfo.setImageNum(groupChatRoomDto.getRoomImageNum());
            chatRoom.getChatMemberList().add(receiverInfo);
            chatMemberRepository.save(receiverInfo);

            manyMemberBuilder.append(",").append(receiver.getId());
        }

        String manyMember = manyMemberBuilder.toString();
        chatRoom.setMembers(manyMember);

        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    // 멤버별 채팅방 조회 (채팅방 별 마지막 채팅시간 기준으로 정렬)
    public List<GetMemberChatRoomDto> getMemberChatRoom(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();

        List<ChatMember> chatMemberList = member.getChatMemberList();
        List<GetMemberChatRoomDto> returnList = new ArrayList<>();
        for (ChatMember chatMember : chatMemberList) {
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
        for (ChatMember chatMember : member.getChatMemberList()) {
            Long roomId = chatMember.getChatRoom().getRoomId();
            roomNumList.add(roomId);
        }

        return roomNumList;
    }

    public ChatMember changeRoomNameAndImageNum(ChangeRoomNameDto changeRoomNameDto) {
        Optional<Member> optionalMember = memberRepository.findById(changeRoomNameDto.getMemberId());
        Member member = optionalMember.get();

        for (ChatMember chatMember : member.getChatMemberList()) {
            ChatRoom chatRoom = chatMember.getChatRoom();
            if (chatRoom.getRoomId().equals(changeRoomNameDto.getRoomId())) {
                chatMember.setRoomName(changeRoomNameDto.getRoomName());
                chatMember.setImageNum(changeRoomNameDto.getRoomImageNum());
                return chatMember;
            }
        }
        return null;
    }

    public Long findExistSoloChatRoom(Long myId, Long receiverId) {
        Member member = memberRepository.findById(myId).get();

        List<ChatMember> chatMemberList = member.getChatMemberList();
        for (ChatMember chatMember : chatMemberList) {
            ChatRoom chatRoom = chatMember.getChatRoom();
            if (chatRoom.getMembers().length() == 3) {
                String[] array = chatRoom.getMembers().split(",");
                for (String s : array) {
                    if (s.equals(receiverId.toString())) {
                        return chatRoom.getRoomId();
                    }
                }
            }
        }

        return null;
    }

    public ChatRoom quitChatRoom(Long roomId, Long targetId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        ChatMember chatMember = chatMemberRepository.findByChatRoom_RoomIdAndMember_Id(roomId, targetId);

        chatRoom.getChatMemberList().remove(chatMember);
        chatMemberRepository.delete(chatMember);

        String[] memberArray = chatRoom.getMembers().split(",");
        String[] newMemberArray = removeElement(memberArray, targetId.toString());

        StringBuilder sb = new StringBuilder();
        sb.append(newMemberArray[0]);
        for(int i=1; i < newMemberArray.length; i++) {
            sb.append(",").append(newMemberArray[i]);
        }
        chatRoom.setMembers(sb.toString());

        return chatRoom;
    }

    public String[] removeElement(String[] arr, String item) {
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        list.remove(item);
        return list.toArray(String[]::new);
    }
}