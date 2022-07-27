package com.fine_server.service.follow;

import com.fine_server.entity.Follow;
import com.fine_server.entity.FollowDto;
import com.fine_server.entity.Member;
import com.fine_server.repository.FollowRepository;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * written by eunhye
 * date: 22.07.20
 */


@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    // 팔로우
    public Follow make(Long friendId, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Member> friend = memberRepository.findById(friendId);

        FollowDto followDto = new FollowDto();
        followDto.setMember(member.get());
        followDto.setFriend(friend.get());

        Follow follow = followRepository.save(followDto.toEntity());

        return follow;
    }

    // 팔로우 취소
    public Long deleteById(Long followId) {
        followRepository.deleteById(followId);
        return followId;
    }

    // 팔로우 리스트
    public List<FollowDto> getFollowList(Long memberId) {
        List<Follow> followList = followRepository.findFriends(memberId);
        List<FollowDto> followDtos = new ArrayList<>();

        for(Follow follow: followList) {
            FollowDto followDto = new FollowDto(follow.getFriend().getId(), follow.getFriend().getNickname(),
                    follow.getFriend().getIntro(), follow.getFriend().getLevel());

            followDtos.add(followDto);
        }
        return followDtos;
    }

    //맞팔 수 카운트
    public Integer getFollowBackCount(Long memberId) {
        List<Follow> followList = followRepository.findFriends(memberId);

        int count = 0;
        for(Follow follow: followList) {
            if(followRepository.findByFriendId(follow.getFriend().getId(), memberId) > 0) {
                count++;
            }
        }
        return count;
    }
}
