package com.fine_server.controller.signup.phone;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.controller.signup.dto.ResidenceDto;
import com.fine_server.controller.signup.dto.PhoneRequestDto;
import com.fine_server.controller.signup.dto.PhoneResponseDto;
import com.fine_server.controller.signup.dto.TokenDto;
import com.fine_server.entity.Member;
import com.fine_server.entity.MemberDetail;
import com.fine_server.repository.MemberRepository;
import com.fine_server.service.mypage.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private InfraData infraData;
    private final DefaultMessageService messageService;

    public AuthController() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCS3GI6MWOPFXKTB", "AP3FMR4GFEPHD0DIM1DUXBOTZPGPWV6A", "https://api.coolsms.co.kr");
    }

    /**
     * 지역인증
     */
    @PostMapping("/mypage/residence/{memberId}")
    public ResponseEntity residenceVerification(@PathVariable Long memberId, @RequestBody @Valid ResidenceDto residenceDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        ResidenceDto residenceResponseDto = authService.residenceAuth(memberId, residenceDto);
        return ResponseEntity.ok(residenceResponseDto);

    }


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
    public ResponseEntity phoneVerification(HttpServletRequest request, @RequestBody @Valid TokenDto tokenDto, @PathVariable Long memberId, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        PhoneResponseDto dto = authService.phoneVerification(request.getSession(), tokenDto.getToken(),tokenDto);

        if(dto == null){
            throw new UserException("인증번호가 맞지 않습니다.");
        } else{
            request.getSession().invalidate();
            PhoneResponseDto phoneResponseDto = authService.phoneAuth(memberId, dto);
            return ResponseEntity.ok(phoneResponseDto);
        }
    }
}
