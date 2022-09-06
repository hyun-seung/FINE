package com.fine_server.controller.signup.phone;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.controller.signup.dto.ResidenceDto;
import com.fine_server.controller.signup.dto.PhoneRequestDto;
import com.fine_server.controller.signup.dto.PhoneResponseDto;
import com.fine_server.controller.signup.dto.TokenDto;
import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.KeywordDto;
import com.fine_server.repository.KeywordRepository;
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
 * date: 22.07.20
 */

@Slf4j
@AllArgsConstructor
@Controller
public class PhoneController {
    private PhoneService phoneService;
    private MemberRepository memberRepository;
    private InfraData infraData;
    private final DefaultMessageService messageService;
    private KeywordRepository keywordRepository;

    public PhoneController() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCS3GI6MWOPFXKTB", "AP3FMR4GFEPHD0DIM1DUXBOTZPGPWV6A", "https://api.coolsms.co.kr");
    }

    //지역인증
    @PostMapping("/mypage/residence/{memberId}")
    public ResponseEntity ResidenceVerification(@PathVariable Long memberId, @RequestBody @Valid ResidenceDto residenceDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        Member member = memberRepository.findById(memberId).get();
        member.setUserResidence(residenceDto.getUserResidence());
        member.setLevel((member.getLevel() + 1)); //신뢰도 + 1

        // 키워드에 거주지 넣기
        member.setKeyword2(splitResidence(residenceDto.getUserResidence()));

        return ResponseEntity.ok().build();
    }

    public String splitResidence(String s) {
        String[] st;
        if (s != null) {
            st = s.split("\\s");
            return st[0];
        }
        else return "미인증";
    }


    @PostMapping("/mypage/phone/{memberId}")
    public ResponseEntity<PhoneRequestDto> sendOne(HttpServletRequest request, @PathVariable Long memberId,
                                                   @RequestBody @Valid PhoneRequestDto phoneRequestDto) {
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
    public ResponseEntity emailVerification(HttpServletRequest request, @RequestBody @Valid TokenDto tokenDto, @PathVariable Long memberId, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        PhoneResponseDto dto = phoneService.phoneVerification(request.getSession(), tokenDto.getToken());

        if(dto == null){
            throw new UserException("인증번호가 맞지 않습니다.");
        } else{
            request.getSession().invalidate();

            Optional<Member> fineMember = memberRepository.findById(memberId);
            Member member = fineMember.get();

            member.setUserPhoneNumber(dto.getPhoneNumber());
            member.setLevel((member.getLevel() + 1)); //신뢰도 + 1

            return ResponseEntity.ok().build();
        }
    }
}
