package com.fine_server.service.mypage;

import com.fine_server.controller.signup.dto.*;
import com.fine_server.entity.Member;
import com.fine_server.entity.MemberDetail;
import com.fine_server.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

/**
 * written by dahae
 * date: 22.07.18
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final MemberRepository memberRepository;

    public boolean isValidToken(HttpSession session,String token) {
        return (session.getAttribute("token")).equals(token);
    }

    public void completeSignUp(HttpSession session) {
        session.setAttribute("phoneVerified", true);
    }

    public AuthDto verification(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        MemberDetail memberDetail = member.getMemberDetail();
        AuthDto dto = new AuthDto();
        dto.setUniversity(memberDetail.getUserUniversity());
        dto.setUpdateDateUniver(memberDetail.getUpdateDateUniversity());

        dto.setUserResidence(memberDetail.getUserResidence());
        dto.setUpdateDateResiden(memberDetail.getUpdateDateResidence());

        dto.setPhoneNumber(memberDetail.getUserPhoneNumber());
        dto.setUpdateDatePhone(memberDetail.getUpdateDatePhone());
        return dto;
    }

    public PhoneResponseDto phoneVerification(HttpSession session, String token, @Valid PhoneResponseDto tokenDto){
        if (!isValidToken(session,token)) {
            return null; 
        }
        completeSignUp(session);
        return createAccountResponseDto(session, tokenDto);
    }

    public PhoneResponseDto createAccountResponseDto(HttpSession session, @Valid PhoneResponseDto tokenDto){
        PhoneResponseDto dto = new PhoneResponseDto();
        dto.setPhoneNumber((String) session.getAttribute("phone"));
        dto.setPhoneVerified((Boolean)session.getAttribute("phoneVerified"));
        dto.setUpdateDate(tokenDto.getUpdateDate());
        return dto;
    }

    public ResidenceDto residenceAuth(Long memberId, ResidenceDto residenceDto) {
        Member member = memberRepository.findById(memberId).get();
        MemberDetail memberDetail = member.getMemberDetail();
        memberDetail.setUserResidence(residenceDto.getUserResidence());
        memberDetail.setUpdateDateResidence(residenceDto.getUpdateDate());
        member.setLevel((member.getLevel() + 1)); //신뢰도 + 1

        return residenceDto;
    }

    public UniversityDto universityAuth(Long memberId, UniversityDto universityDto) {
        Member member = memberRepository.findById(memberId).get();
        MemberDetail memberDetail = member.getMemberDetail();
        memberDetail.setUserResidence(universityDto.getUniversity());
        memberDetail.setUpdateDateUniversity(universityDto.getUpdateDate());
        member.setLevel((member.getLevel() + 1)); //신뢰도 + 1

        return universityDto;
    }

    public PhoneResponseDto phoneAuth(Long memberId, PhoneResponseDto phoneResponseDto) {
        Member member = memberRepository.findById(memberId).get();
        MemberDetail memberDetail = member.getMemberDetail();
        memberDetail.setUserPhoneNumber(phoneResponseDto.getPhoneNumber());
        memberDetail.setUpdateDatePhone(phoneResponseDto.getUpdateDate());

        member.setLevel((member.getLevel() + 1)); //신뢰도 + 1
        return phoneResponseDto;
    }

    public ResidenceDto updateResidenceAuth(Long memberId, ResidenceDto residenceDto) {
        Member member = memberRepository.findById(memberId).get();
        MemberDetail memberDetail = member.getMemberDetail();
        memberDetail.setUserResidence(residenceDto.getUserResidence());
        memberDetail.setUpdateDateResidence(residenceDto.getUpdateDate());
        memberRepository.save(member);

        return residenceDto;
    }

    public PhoneResponseDto updatePhoneAuth(Long memberId, PhoneResponseDto phoneResponseDto) {
        Member member = memberRepository.findById(memberId).get();
        MemberDetail memberDetail = member.getMemberDetail();
        memberDetail.setUserPhoneNumber(phoneResponseDto.getPhoneNumber());
        memberDetail.setUpdateDatePhone(phoneResponseDto.getUpdateDate());
        memberRepository.save(member);

        return phoneResponseDto;
    }

    public UniversityDto updateUniversityAuth(Long memberId, UniversityDto universityDto) {
        Member member = memberRepository.findById(memberId).get();
        MemberDetail memberDetail = member.getMemberDetail();
        memberDetail.setUserResidence(universityDto.getUniversity());
        memberDetail.setUpdateDateUniversity(universityDto.getUpdateDate());
        memberRepository.save(member);

        return universityDto;
    }

}