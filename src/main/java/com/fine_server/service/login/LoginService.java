package com.fine_server.service.login;
import com.fine_server.controller.login.LoginDto;
import com.fine_server.controller.login.LoginRes;
import com.fine_server.controller.login.SessionConst;
import com.fine_server.entity.Member;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginRes login(LoginDto loginDto, HttpServletRequest request) {
        Member member = memberRepository.findByUserId(loginDto.getId());

        if(member == null){ return null; }

        if(passwordEncoder.matches(loginDto.getPassword(),member.getPassword())){
            HttpSession session = request.getSession();
            LoginSession loginSession = new LoginSession(loginDto.getId(), loginDto.getPassword());
            session.setAttribute(SessionConst.LOGIN_MEMBER, loginSession);
            return new LoginRes(member);
        }
        else {
            throw new IllegalArgumentException("아이디 비밀번호가 일치하지 않습니다.");
        }

    }
}
