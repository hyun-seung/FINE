package com.fine_server.service.mypage;

import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.MemberRequestDto;
import com.fine_server.repository.MemberRepository;
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

    // save account for test
    public Member saveNewAccount(MemberRequestDto memberDto) {
        memberRepository.save(memberDto.toEntity());
        return memberDto.toEntity();
    }

    public Member editProfile(Long memberId, MemberRequestDto memberDto) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        member.setIntro(memberDto.getIntro());
        member.setNickname(memberDto.getNickname());
        memberRepository.save(member);

        return memberDto.toEntity();
    }
}
