package com.fine_server.controller.signup;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.controller.signup.dto.MypageDataDto;
import com.fine_server.controller.signup.dto.SignUpDto;
import com.fine_server.controller.signup.dto.UserIdDto;
import com.fine_server.controller.signup.dto.UserNickNameDto;
import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.MemberRequestDto;
import com.fine_server.repository.MemberRepository;
import com.fine_server.service.mypage.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * written by dahae
 * date: 22.07.15
 */
@Slf4j
@Controller
@AllArgsConstructor
public class SignUpController {
    private final MemberService memberService;

    @PostMapping("/idVerification")
    public ResponseEntity<Member> idVerification(@RequestBody UserIdDto userIdDto, BindingResult bindingResult, Errors errors) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        UserIdDto checkUserId = memberService.vaildCheckUserId(userIdDto);
        return new ResponseEntity(checkUserId, HttpStatus.OK);
    }

    @PostMapping("/nickNameVerification")
    public ResponseEntity<Member> nickNameVerification(@RequestBody UserNickNameDto userNickNameDto, BindingResult bindingResult, Errors errors) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        UserNickNameDto checkNickName = memberService.vaildCheckNickName(userNickNameDto);
        return new ResponseEntity(checkNickName, HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<Member> signUp(@RequestBody MemberRequestDto memberDto, BindingResult bindingResult, Errors errors) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        Member member = memberService.saveNewAccount(memberDto);
        SignUpDto signUpDto = new SignUpDto(member.getNickname(), member.getUserImageNum(), member.getIntro(),
                member.getKeyword1(), member.getKeyword2(), member.getKeyword3(),1);
        return new ResponseEntity(signUpDto, HttpStatus.OK);
    }
}
