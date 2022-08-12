package com.fine_server.repository;

import com.fine_server.entity.Follow;
import com.fine_server.entity.FollowDto;
import com.fine_server.entity.Member;
import com.fine_server.entity.posting.GetMemberDto;

import java.util.List;

public interface FollowCustomRepository {

    List<Follow> findFriends(Long memberId);

    Long findByMemberIdAndFriendId(Long memberId, Long friendId);

    List<Member> findByNickname(Long memberId, String nickname);
}

