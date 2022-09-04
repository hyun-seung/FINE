package com.fine_server.service.mypage;

import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.MemberRequestDto;
import com.fine_server.repository.MemberRepository;
import com.fine_server.service.follow.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * written by dahae
 * date: 22.05.27
 *
 * edit by dahae
 * date: 22.07.15
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final FollowService followService;

    public Optional<Member> findMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        member.setFollowBack(followService.getFollowBackCount(memberId));
        return Optional.of(member);
    }

    public Member saveNewAccount(MemberRequestDto memberDto) {
        Member member = memberDto.toEntity();
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(member);
        return memberDto.toEntity();
    }

    public Member editProfile(Long memberId, MemberRequestDto memberDto) {
       Member member = memberRepository.findById(memberId).get();
       member.setIntro(memberDto.getIntro());
       member.setNickname(memberDto.getNickname());
       member.setUserImageNum(memberDto.getUserImageNum());
       member.setKeyword1(memberDto.getKeyword1());
//       member.setKeyword2(memberDto.getKeyword2());
//       member.setKeyword3(memberDto.getKeyword3());

       memberRepository.save(member);

       return memberDto.toEntity();
    }

    public Long deleteAccount(Long id) {
        memberRepository.deleteById(id);
        return id;
    }

}
