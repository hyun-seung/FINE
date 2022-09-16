package com.fine_server.service.mypage;

import com.fine_server.controller.signup.dto.AccountResponseDto;
import com.fine_server.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MailService {

    private JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void mailSend(HttpSession session) throws UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //네이버 인증
        String host = "smtp.naver.com";
        String user = "fine_service@naver.com";
        String password = "$$finefine";

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // 이미지 넣기 위해 true로
            mimeMessageHelper.setTo((String) session.getAttribute("email"));
            //mimeMessageHelper.setFrom(MailService.FROM_ADDRESS);
            mimeMessageHelper.setFrom(new InternetAddress(user, "FINE"));
            mimeMessageHelper.setSubject("FINE 서비스 사용을 위해 코드를 복사하여 붙여넣어주세요.");

            Context context = new Context(); // model에 내용담아주듯이
            context.setVariable("token",(String) session.getAttribute("token"));
            context.setVariable("username", (String) session.getAttribute("email"));
            context.setVariable("message","FINE 서비스 사용을 위해 코드를 복사하여 붙여넣어주세요.");

            String message = templateEngine.process("mail/emailAuth_Template", context);

            mimeMessageHelper.setText(message,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
    }

    public String generateEmailCheckToken() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 6);
        return uuid;
    }

    public void sendEmailCheckToken(HttpSession session) throws UnsupportedEncodingException {
        session.setAttribute("token", generateEmailCheckToken());
        mailSend(session);
    }

    public boolean isValidToken(HttpSession session,String token) {return (session.getAttribute("token")).equals(token);}

    public void completeSignUp(HttpSession session) {session.setAttribute("emailVerified", true);}

    public AccountResponseDto emailVerification(HttpSession session, String token){
        if (!isValidToken(session,token)) {
            return null; 
        }
        completeSignUp(session);
        return createAccountResponseDto(session);
    }

    public AccountResponseDto createAccountResponseDto(HttpSession session){
        AccountResponseDto dto = new AccountResponseDto();
        dto.setEmail((String) session.getAttribute("email"));
        dto.setEmailVerified((Boolean)session.getAttribute("emailVerified"));
        return dto;
    }

    //public Boolean existsEmail(MailDto mailDto){
        //return memberRepository.existsByEmail(mailDto.getAddress());
    //}
}