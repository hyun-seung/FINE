package com.fine_server.service.mypage;

import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.MemberRequestDto;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

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
    private final PasswordEncoder passwordEncoder;
    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member saveNewAccount(MemberRequestDto memberDto) {
        Member member = memberDto.toEntity();
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(member);
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
