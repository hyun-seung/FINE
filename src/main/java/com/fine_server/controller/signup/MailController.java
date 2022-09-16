package com.fine_server.controller.signup;


import com.fine_server.controller.mypage.errors.ErrorResult;
import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.controller.signup.dto.AccountResponseDto;
import com.fine_server.controller.signup.dto.MailDto;
import com.fine_server.controller.signup.dto.TokenDto;
import com.fine_server.controller.signup.dto.UniversityDto;
import com.fine_server.entity.Member;
import com.fine_server.service.mypage.AuthService;
import com.fine_server.service.mypage.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@AllArgsConstructor
public class MailController {
    private final MailService mailService;
    private AuthService authService;
    @PostMapping("/mail")
    public ResponseEntity execMail(HttpServletRequest request, @RequestBody @Valid MailDto mailDto, Errors errors, BindingResult bindingResult) throws UnsupportedEncodingException {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

//       if(mailService.existsEmail(mailDto)){
//            throw new UserException("이미 존재하는 이메일입니다.");
//        }

        //세션 생성
        HttpSession session = request.getSession();
        String email = mailDto.getAddress();
        session.setAttribute("email", email);

        mailService.sendEmailCheckToken(session);

        return ResponseEntity.ok().build();
    }

    //대학인증과 이메일 인증을 한번에
    @PostMapping("/emailVerification/{memberId}")
    public ResponseEntity emailVerification(HttpServletRequest request, @PathVariable Long memberId, @RequestBody @Valid UniversityDto universityDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        AccountResponseDto dto = mailService.emailVerification(request.getSession(), universityDto.getToken());

        //인증번호 맞지 않음
        if(dto == null){
            throw new UserException("인증번호가 맞지 않습니다.");
        } else{
            //인증이 완료 -> 세션 초기화
            request.getSession().invalidate();
            UniversityDto universityAuth = authService.universityAuth(memberId, universityDto);
            return ResponseEntity.ok(universityAuth);
        }
    }

    /**
     * 대학인증 업데이트
     */

    //대학인증과 이메일 인증을 한번에
    @PutMapping("/emailVerification/{memberId}")
    public ResponseEntity universityUpdate(HttpServletRequest request, @PathVariable Long memberId, @RequestBody @Valid UniversityDto universityDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        AccountResponseDto dto = mailService.emailVerification(request.getSession(), universityDto.getToken());

        //인증번호 맞지 않음
        if(dto == null){
            throw new UserException("인증번호가 맞지 않습니다.");
        } else{
            //인증이 완료 -> 세션 초기화
            request.getSession().invalidate();
            UniversityDto universityAuth = authService.updateUniversityAuth(memberId, universityDto);
            return ResponseEntity.ok(universityAuth);
        }
    }

   @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler (UserException e) {
        ErrorResult errorResult = new ErrorResult("사용자 입력값 오류",e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

}