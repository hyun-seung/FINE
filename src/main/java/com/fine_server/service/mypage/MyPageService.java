package com.fine_server.service.mypage;

import com.fine_server.controller.mypage.Plan;
import com.fine_server.entity.Member;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Page<Plan> getMyPost(Long memberId, Pageable pageable) {
        Optional<Member> user = memberRepository.findById(memberId);

        Page<Plan> plans = postRepository.findByPlanManagerAndPublished(user, true, pageable);
        return plans;
    }
}
