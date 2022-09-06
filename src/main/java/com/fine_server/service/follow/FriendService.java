package com.fine_server.service.follow;

import com.fine_server.repository.FollowRepository;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * written by eunhye
 * date: 22.08.07
 */


@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

//    public List<MemberResponseDto> recommendFriends(Long memberId) {
//
//    }

}
