package com.fine_server.service.mypage;

import com.fine_server.controller.mypage.Plan;
import com.fine_server.entity.Member;
import com.fine_server.repository.MemberRepository;
import com.fine_server.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 * written by dahae
 * date: 22.06.22
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MyPageService {
    private final MemberRepository memberRepository;
    private final PostingRepository postingRepository;

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> getMyPost(Long memberId, Pageable pageable) {
        Optional<Member> user = memberRepository.findById(memberId);

        List<Member> memberPosts = postingRepository.findByMemberId(memberId);

        return memberPosts;
    }
}
