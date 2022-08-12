package com.fine_server.repository;

import com.fine_server.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FollowRepository extends JpaRepository<Follow, Long>, FollowCustomRepository {
    Long deleteByFriendIdAndMemberId(Long friendId, Long memberId);
//    Boolean findByMemberIdAndFriendId(Long memberId, Long FriendId);

//    List<FollowDto> findByMember_IdAndFriend_NicknameContaining(Long memberId, String nickname);
}
