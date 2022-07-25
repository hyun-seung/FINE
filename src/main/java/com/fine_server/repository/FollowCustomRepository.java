package com.fine_server.repository;

import com.fine_server.entity.Follow;

import java.util.List;

public interface FollowCustomRepository {

    List<Follow> findFriends(Long memberId);
}
