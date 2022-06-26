package com.fine_server.controller.mypage;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.entity.Keyword;
import com.fine_server.entity.Posting;
import com.fine_server.entity.mypage.MemberResponseDto;
import com.fine_server.service.mypage.KeywordService;
import com.fine_server.service.mypage.MemberService;
import com.fine_server.controller.mypage.errors.ErrorResult;
import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.MemberRequestDto;
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
import java.util.ArrayList;
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
    private final KeywordService keywordService;

    @PostMapping("/signUp")
    public ResponseEntity<Member> signUp(@RequestBody @Valid MemberRequestDto memberDto, BindingResult bindingResult, Errors errors) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        Member member = memberService.saveNewAccount(memberDto);

        return new ResponseEntity(member,HttpStatus.OK);
    }

    /**
     * edit. 22.06.26
     */
    @PostMapping("/myPage/editProfile/{memberId}")
    public ResponseEntity<Member> editProfile(@RequestBody @Valid MemberRequestDto memberRequestDto, @PathVariable Long memberId, BindingResult bindingResult, Errors errors) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        Member member = memberService.editProfile(memberId,memberRequestDto);
        List<String> keywordList = keywordService.save(member, memberRequestDto.getKeyword());//키워드 저장

        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getNickname(),member.getIntro(),keywordList);
        return new ResponseEntity(memberResponseDto,HttpStatus.OK);
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

    /**
     * edit. 22.06.26
     * 친구 프로필 조회
     */
    @ResponseBody
    @GetMapping("/profile/{memberId}")
    public ResponseEntity<Member> profile(@PathVariable Long memberId){
        Optional<Member> findMember = memberService.findMember(memberId);

        if (!findMember.isEmpty()){
            MemberResponseDto memberResponseDto = getMemberResponseDto(findMember);
            return new ResponseEntity(memberResponseDto, HttpStatus.OK);
        }else{
            throw new UserException("사용자를 찾지 못하였습니다.");
        }
    }

    private MemberResponseDto getMemberResponseDto(Optional<Member> findMember) {
        Member member = findMember.get();
        List<Keyword> keywordEntityList = keywordService.findByMemberId(member);
        List<String> keywordList = new ArrayList<>();

        for(Keyword keyword: keywordEntityList) {
            keywordList.add(keyword.getKeyword());
        }

        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getNickname(),member.getIntro(),keywordList);
        return memberResponseDto;
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

    /**
     * add. 22.06.26
     */
//    @ResponseBody
//    @GetMapping("/myPage/myBookMark/{memberId}")
//    public ResponseEntity myBookMark(@PathVariable Long memberId){
//        List<Posting> posts = myPageService.getMyBookMark(memberId);
//        return ResponseEntity.ok(posts);
//
//    }



    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler (UserException e) {
        ErrorResult errorResult = new ErrorResult("사용자를 찾지 못하였습니다.",e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

}



