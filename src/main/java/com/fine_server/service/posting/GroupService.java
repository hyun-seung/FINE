package com.fine_server.service.posting;

import com.fine_server.repository.GroupRepository;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {

    MemberRepository memberRepository;
    GroupRepository groupRepository;

//    // 그룹 생성
//    public Group make(Long memberId, GroupDto groupDto) {
//        Group save = groupRepository.save(groupDto.toEntity());
//        return save;
//    }

}
