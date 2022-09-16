package com.fine_server.service.mypage;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.controller.signup.dto.UserIdDto;
import com.fine_server.controller.signup.dto.UserNickNameDto;
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

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

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
        Member save = memberRepository.save(member);
        return save;
    }

    public UserIdDto vaildCheckUserId(UserIdDto userIdDto){
        if(memberRepository.existsByUserId(userIdDto.getUserId())){
            throw new UserException("이미 존재하는 아이디입니다.");
        }
        return userIdDto;
    }

    public UserNickNameDto vaildCheckNickName(UserNickNameDto userNickNameDto){
        if(memberRepository.existsByUserId(userNickNameDto.getUserNickName())){
            throw new UserException("이미 존재하는 닉네임입니다.");
        }
        return userNickNameDto;
    }

    public Member editProfile(Long memberId, MemberRequestDto memberDto) {
       Member member = memberRepository.findById(memberId).get();
       member.setIntro(memberDto.getIntro());
       member.setNickname(memberDto.getNickname());
       member.setUserImageNum(memberDto.getUserImageNum());
       member.setKeyword1(memberDto.getKeyword1());
       member.setKeyword2(memberDto.getKeyword2());
       member.setKeyword3(memberDto.getKeyword3());

       memberRepository.save(member);

       return memberDto.toEntity();
    }

    public Long deleteAccount(Long id) {
        memberRepository.deleteById(id);
        return id;
    }
}
