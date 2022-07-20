package com.fine_server.service.mypage;

import com.fine_server.controller.signup.dto.PhoneResponseDto;
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
public class PhoneService {
    public boolean isValidToken(HttpSession session,String token) {
        return (session.getAttribute("token")).equals(token);
    }

    public void completeSignUp(HttpSession session) {
        session.setAttribute("phoneVerified", true);
    }

    public PhoneResponseDto phoneVerification(HttpSession session, String token){
        if (!isValidToken(session,token)) {
            return null; 
        }
        completeSignUp(session);
        return createAccountResponseDto(session);
    }

    public PhoneResponseDto createAccountResponseDto(HttpSession session){
        PhoneResponseDto dto = new PhoneResponseDto();
        dto.setPhoneNumber((String) session.getAttribute("phone"));
        dto.setPhoneVerified((Boolean)session.getAttribute("phoneVerified"));
        return dto;
    }
}