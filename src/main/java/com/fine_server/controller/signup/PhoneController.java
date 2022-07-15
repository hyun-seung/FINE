package com.fine_server.controller.signup;

import lombok.AllArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PhoneController {
    private final DefaultMessageService messageService;

    public PhoneController() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCS3GI6MWOPFXKTB", "AP3FMR4GFEPHD0DIM1DUXBOTZPGPWV6A", "https://api.coolsms.co.kr");
    }
    /**
     * 단일 메시지 발송 예제
     */
    @PostMapping("/authMessage")
    public SingleMessageSentResponse sendOne() {
        Message message = new Message();

        message.setFrom("전송할 번호");
        message.setTo("전송될 번호");
        message.setText("다음의 인증번호를 입력해주세요");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }
}
