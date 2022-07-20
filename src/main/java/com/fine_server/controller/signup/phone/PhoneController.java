package com.fine_server.controller.signup.phone;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.controller.signup.dto.PhoneDto;
import com.fine_server.controller.signup.dto.PhoneResponseDto;
import com.fine_server.controller.signup.dto.TokenDto;
import com.fine_server.entity.Member;
import com.fine_server.repository.MemberRepository;
import com.fine_server.service.mypage.PhoneService;
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
import java.util.Optional;
import java.util.UUID;

/**
 * written by dahae
 * date: 22.07.15
 */
//수정
    //깃 이그노어 적용
@Slf4j
@AllArgsConstructor
@Controller
public class PhoneController {
    private PhoneService phoneService;
    private MemberRepository memberRepository;
    private PhoneNum phoneNum;
    private final DefaultMessageService messageService;

    public PhoneController() {
        this.messageService = NurigoApp.INSTANCE.initialize(phoneNum.getApiKey(), phoneNum.getApiSecretKey(), "https://api.coolsms.co.kr");
    }

    @PostMapping("/authMessage/{memberId}")
    public ResponseEntity<PhoneDto> sendOne(HttpServletRequest request, @PathVariable Long memberId, @RequestBody @Valid PhoneDto phoneDto, BindingResult bindingResult) {
        Message message = new Message();

        HttpSession session = request.getSession();
        session.setAttribute("id", memberId);
        session.setAttribute("token", generateCheckToken());

        message.setFrom(phoneNum.getPhoneNum());
        message.setTo(phoneDto.getPhoneNumber());
        message.setText("안녕하세요 FINE입니다.\n\n다음의 인증번호를 입력해주세요!!\n\n" + (String) session.getAttribute("token"));

        this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return new ResponseEntity(phoneDto, HttpStatus.OK);
    }

    public String generateCheckToken() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 10);
        return uuid;
    }

    @PostMapping("/phoneVerification/{memberId}")
    public ResponseEntity emailVerification(HttpServletRequest request, @RequestBody @Valid TokenDto tokenDto, @PathVariable Long memberId, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        PhoneResponseDto dto = phoneService.phoneVerification(request.getSession(), tokenDto.getToken());

        if(dto == null){ //인증번호 맞지 않음
            throw new UserException("인증번호가 맞지 않습니다.");
        } else{
            request.getSession().invalidate();

            Optional<Member> fineMember = memberRepository.findById(memberId);
            Member member = fineMember.get();

            member.setUserPhoneNumber(dto.getPhoneNumber());
            return ResponseEntity.ok().build();
        }
    }
}
