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
 * date: 22.07.29
 */


@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    // 팔로우 - 전체를 리턴하는 거에서 friend만 리턴으로 리팩토링 예정
    public Follow make(Long friendId, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Member> friend = memberRepository.findById(friendId);

        FollowDto followDto = new FollowDto();
        followDto.setMember(member.get());
        followDto.setFriend(friend.get());
        followRepository.save(followDto.toEntity());

        return followDto.toEntity();
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

    //맞팔 수 카운트 - 친구 페이지에만 반환 중, 수정 필요
    public Integer getFollowBackCount(Long memberId) {
        List<Follow> followList = followRepository.findFriends(memberId); //해당 멤버가 팔로우 한 리스트

        int count = 0;
        for(Follow follow: followList) { //팔로우 리스트에서 해당 사용자를 팔로우 한 멤버 탐색
            if(followRepository.findByMemberIdAndFriendId(follow.getFriend().getId(), memberId) > 0) {
                count++;
            }
        }
        return count;
    }

    //팔로우 검색
    public List<FollowDto> searchFollow(Long memberId, String nickname) {
        List<FollowDto> followList = followRepository.findByNickname(memberId, nickname); //검색 결과

        return followList;
    }
}
