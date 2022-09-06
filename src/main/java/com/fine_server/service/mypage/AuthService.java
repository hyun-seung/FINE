package com.fine_server.service.mypage;

import com.fine_server.controller.signup.dto.PhoneResponseDto;
import com.fine_server.controller.signup.dto.ResidenceDto;
import com.fine_server.controller.signup.dto.TokenDto;
import com.fine_server.entity.Member;
import com.fine_server.entity.MemberDetail;
import com.fine_server.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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

    public PhoneResponseDto phoneVerification(HttpSession session, String token, TokenDto tokenDto){
        if (!isValidToken(session,token)) {
            return null; 
        }
        completeSignUp(session);
        return createAccountResponseDto(session, tokenDto);
    }

    public PhoneResponseDto createAccountResponseDto(HttpSession session, TokenDto tokenDto){
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

    public PhoneResponseDto phoneAuth(Long memberId, PhoneResponseDto phoneResponseDto) {
        Member member = memberRepository.findById(memberId).get();
        MemberDetail memberDetail = member.getMemberDetail();
        memberDetail.setUserPhoneNumber(phoneResponseDto.getPhoneNumber());
        memberDetail.setUpdateDatePhone(phoneResponseDto.getUpdateDate());

        member.setLevel((member.getLevel() + 1)); //신뢰도 + 1
        return phoneResponseDto;
    }
}