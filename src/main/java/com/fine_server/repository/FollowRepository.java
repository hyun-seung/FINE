package com.fine_server.repository;

import com.fine_server.entity.Follow;
import com.fine_server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowCustomRepository {

//    Boolean findByMemberIdAndFriendId(Long memberId, Long FriendId);
}
