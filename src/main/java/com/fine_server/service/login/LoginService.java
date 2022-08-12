package com.fine_server.service.login;
import com.fine_server.entity.Member;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * @return null이면 로그인 실패
     */
    public Member login(String userid, String password) {
        Member member = memberRepository.findByUserId(userid);

        if(member == null){ return null; }

        if(passwordEncoder.matches(password,member.getPassword())){ return member; }

        else { return null; }

    }
}
