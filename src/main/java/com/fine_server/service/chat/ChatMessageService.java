package com.fine_server.service.chat;

import com.fine_server.entity.ChatMessage;
import com.fine_server.entity.ChatRoom;
import com.fine_server.entity.Member;
import com.fine_server.entity.chat.ChatMessageDto;
import com.fine_server.repository.ChatMessageRepository;
import com.fine_server.repository.ChatRoomRepository;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageService {

    private final MemberRepository memberRepository;

    private final ChatMessageRepository chatMessageRepository;

    private final ChatRoomRepository chatRoomRepository;

    public void makeChatMessage(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = chatMessageDto.toEntity();

        Member member = findMember(chatMessageDto.getMemberId());
        chatMessage.setMember(member);

        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(chatMessageDto.getRoomId());
        ChatRoom chatRoom = optionalChatRoom.get();
        chatMessage.setChatRoom(chatRoom);

        chatMessageRepository.save(chatMessage);
    }

    public Member findMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();
        return member;
    }
}
