package com.fine_server.controller.login;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.entity.Member;
import com.fine_server.service.login.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * written by dahae
 * date: 22.08.03
 */
@Slf4j
@Controller
@AllArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginRes signUp(HttpServletRequest request, @RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {

        // 아이디 또는 비밀번호에 빈 문자열이 들어온 경우
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("아이디 비밀번호를 입력해주세요");
        }

        return loginService.login(loginDto, request);
    }

    @GetMapping("/logout")
    public LogoutRes logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new IllegalArgumentException("로그인 정보가 없는 사용자입니다.");
        }
        session.invalidate();
        return new LogoutRes("정상 로그아웃 처리되었습니다.");
    }
}
