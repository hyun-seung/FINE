package com.fine_server.controller.mypage;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.entity.Posting;
import com.fine_server.service.mypage.MemberService;
import com.fine_server.controller.mypage.errors.ErrorResult;
import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.MemberDto;
import com.fine_server.service.mypage.MyPageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * written by dahae
 * date: 22.05.27
 */

@Slf4j
@Controller
@AllArgsConstructor
public class MyPageController {
    private final MemberService memberService;
    private final MyPageService myPageService;

    @PostMapping("/signUp")
    public ResponseEntity<Member> signUp(@RequestBody @Valid MemberDto memberDto, BindingResult bindingResult, Errors errors) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        Member member = memberService.saveNewAccount(memberDto);

        return new ResponseEntity(member,HttpStatus.OK);
    }

    @PostMapping("/myPage/editProfile/{memberId}")
    public ResponseEntity<Member> editProfile(@RequestBody @Valid MemberDto memberDto, @PathVariable Long memberId, BindingResult bindingResult, Errors errors) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        Member member = memberService.editProfile(memberId,memberDto);

        return new ResponseEntity(member,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/myPage/{memberId}")
    public ResponseEntity<Member> profileTest(@PathVariable Long memberId){
        Optional<Member> findMember = memberService.findMember(memberId);
        if (!findMember.isEmpty()){
            return new ResponseEntity(findMember, HttpStatus.OK);
        }else{
            throw new UserException("사용자를 찾지 못하였습니다.");
        }
    }


    //친구 프로필 조회
    @ResponseBody
    @GetMapping("/profile/{memberId}")
    public ResponseEntity<Member> profile(@PathVariable Long memberId){
        Optional<Member> findMember = memberService.findMember(memberId);
        if (!findMember.isEmpty()){
            return new ResponseEntity(findMember, HttpStatus.OK);
        }else{
            throw new UserException("사용자를 찾지 못하였습니다.");
        }
    }

    /**
     * add. 22.06.23
     */
    @ResponseBody
    @GetMapping("/myPage/myPost/{memberId}")
    public ResponseEntity myPost(@PathVariable Long memberId){
        List<Posting> posts = myPageService.getMyPost(memberId);
        return ResponseEntity.ok(posts);

    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler (UserException e) {
        ErrorResult errorResult = new ErrorResult("사용자를 찾지 못하였습니다.",e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

}



