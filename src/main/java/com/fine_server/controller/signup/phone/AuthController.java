package com.fine_server.controller.signup.phone;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.controller.signup.dto.*;
import com.fine_server.entity.Member;
import com.fine_server.entity.MemberDetail;
import com.fine_server.repository.MemberRepository;
import com.fine_server.service.mypage.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

/**
 * written by dahae
 * date: 22.07.20
 */

@Slf4j
@AllArgsConstructor
@Controller
public class AuthController {
    private AuthService authService;
    private MemberRepository memberRepository;
    private final DefaultMessageService messageService;

    public AuthController() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCS3GI6MWOPFXKTB", "AP3FMR4GFEPHD0DIM1DUXBOTZPGPWV6A", "https://api.coolsms.co.kr");
    }

    /**
     * 지역인증
     */
    @PostMapping("/mypage/residence/{memberId}")
    public ResponseEntity ResidenceVerification(@PathVariable Long memberId, @RequestBody @Valid ResidenceDto residenceDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        ResidenceDto residenceResponseDto = authService.residenceAuth(memberId, residenceDto);
        return ResponseEntity.ok(residenceResponseDto);

    }

    /**
     * 지역인증 업데이트
     */
    @PutMapping("/mypage/residence/{memberId}")
    public ResponseEntity residenceUpdate(@PathVariable Long memberId, @RequestBody @Valid ResidenceDto residenceDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        ResidenceDto residenceResponseDto = authService.updateResidenceAuth(memberId, residenceDto);
        return ResponseEntity.ok(residenceResponseDto);

    }

    /**
     * 휴대폰 인증
     */
    @PostMapping("/mypage/phone/{memberId}")
    public ResponseEntity<PhoneRequestDto> sendMessage(HttpServletRequest request, @PathVariable Long memberId, @RequestBody @Valid PhoneRequestDto phoneRequestDto) {
        Message message = new Message();

        HttpSession session = request.getSession();
        session.setAttribute("id", memberId);
        session.setAttribute("token", generateCheckToken());

        message.setFrom("01063001337");
        message.setTo(phoneRequestDto.getPhoneNumber());
        message.setText("안녕하세요 FINE입니다.\n\n다음의 인증번호를 입력해주세요!!\n\n" + (String) session.getAttribute("token"));

        this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return new ResponseEntity(phoneRequestDto, HttpStatus.OK);
    }

    public String generateCheckToken() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 10);
        return uuid;
    }

    @PostMapping("/mypage/phone/token/{memberId}")
    public ResponseEntity phoneVerification(HttpServletRequest request, @RequestBody @Valid PhoneResponseDto phoneResponseDto, @PathVariable Long memberId, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        PhoneResponseDto dto = authService.phoneVerification(request.getSession(), phoneResponseDto.getToken(),phoneResponseDto);

        if(dto == null){
            throw new UserException("인증번호가 맞지 않습니다.");
        } else{
            request.getSession().invalidate();
            PhoneResponseDto phoneAuth = authService.phoneAuth(memberId, dto);
            return ResponseEntity.ok(phoneAuth);
        }
    }

    /**
     * 휴대폰인증 업데이트
     */
    @PutMapping("/mypage/phone/token/{memberId}")
    public ResponseEntity phoneUpdate(HttpServletRequest request, @RequestBody @Valid PhoneResponseDto phoneResponseDto, @PathVariable Long memberId, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        PhoneResponseDto dto = authService.phoneVerification(request.getSession(), phoneResponseDto.getToken(),phoneResponseDto);

        if(dto == null){
            throw new UserException("인증번호가 맞지 않습니다.");
        } else{
            request.getSession().invalidate();
            PhoneResponseDto phoneAuth = authService.phoneAuth(memberId, dto);
            return ResponseEntity.ok(phoneAuth);
        }
    }

    @PostMapping("/mypage/university/{memberId}")
    public ResponseEntity universityVerification(HttpServletRequest request, @RequestBody @Valid UniversityDto universityDto, @PathVariable Long memberId, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        UniversityDto universityAuth = authService.universityAuth(memberId, universityDto);
        return ResponseEntity.ok(universityAuth);

    }

    /**
     * 대학인증 업데이트 -사용 x
     */
    @PutMapping("/mypage/university/{memberId}")
    public ResponseEntity universityUpdate(@PathVariable Long memberId, @RequestBody @Valid  UniversityDto universityDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        UniversityDto universityAuth = authService.updateUniversityAuth(memberId, universityDto);
        return ResponseEntity.ok(universityAuth);

    }

}
