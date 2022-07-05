package com.fine_server.service.posting;

import com.fine_server.entity.Group;
import com.fine_server.entity.GroupCollection;
import com.fine_server.entity.Posting;
import com.fine_server.entity.Recruiting;
import com.fine_server.entity.posting.GroupCollectionCreateDto;
import com.fine_server.entity.posting.GroupCreateDto;
import com.fine_server.repository.GroupCollectionRepository;
import com.fine_server.repository.GroupRepository;
import com.fine_server.repository.PostingRepository;
import com.fine_server.repository.RecruitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {

    private final PostingRepository postingRepository;
    private final GroupRepository groupRepository;
    private final GroupCollectionRepository groupCollectionRepository;


    // 모집 마감 -> 팀 생성
    public Group makeGroup(Long postingId) {
        Optional<Posting> optionalPosting = postingRepository.findById(postingId);
        Posting posting = optionalPosting.get();
        Group group = groupRepository.save(new GroupCreateDto(posting, posting.getMember()).toEntity());// 그룹 생성
        List<Recruiting> recruitingList = posting.getRecruitingList();
        for(Recruiting temp : recruitingList) {
            if(temp.getAccept_check().equals(true)) {
                GroupCollection groupCollection = groupCollectionRepository.save(new GroupCollectionCreateDto(group, temp.getMember()).toEntity());
                group.getGroupCollectionList().add(groupCollection);
            }
        }
        return group;
    }
}
