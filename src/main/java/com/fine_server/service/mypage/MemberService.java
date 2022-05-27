package com.fine_server.Service.mypage;

import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.MemberDto;
import com.fine_server.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * written by dahae
 * date: 22.05.27
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // save account - test용
    public Member saveNewAccount(MemberDto memberDto) {
        memberRepository.save(memberDto.toEntity());
        return memberDto.toEntity();
    }
}
