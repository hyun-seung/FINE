package com.fine_server.controller.mypage;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.entity.Bookmark;
import com.fine_server.entity.Keyword;
import com.fine_server.entity.Posting;
import com.fine_server.entity.mypage.MemberResponseDto;
import com.fine_server.repository.BookmarkRepository;
import com.fine_server.repository.MemberRepository;
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
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/signuptest")
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
     * 마이페이지 접속 화면
     */
    @ResponseBody
    @GetMapping("/mypage/{memberId}")
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
     */
    @PostMapping("/mypage/editProfile/{memberId}")
    public ResponseEntity<Member> editProfile(@RequestBody @Valid MemberRequestDto memberRequestDto, @PathVariable Long memberId, BindingResult bindingResult, Errors errors) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        Member member = memberService.editProfile(memberId,memberRequestDto);

        //MemberResponseDto memberResponseDto = new MemberResponseDto(member.getNickname(),member.getUserImageNum(),member.getIntro(),keywordList);
        return new ResponseEntity(memberRequestDto,HttpStatus.OK);
    }


    /**
     * edit. 22.06.26
     * 친구 프로필 조회
     */

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

        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getNickname(),member.getUserImageNum(),member.getIntro(),keywordList);
        return memberResponseDto;
    }

    /**
     * add. 22.06.23
     * 내 게시글 조회
     */
    @GetMapping("/mypage/myPost/{memberId}")
    public ResponseEntity myPost(@PathVariable Long memberId){
        List<Posting> posts = myPageService.getMyPost(memberId);
        return ResponseEntity.ok(posts);
    }


    /**
     * add. 22.07.27
     * 내 그룹 신청글 조회
     */
    @GetMapping("/mypage/myGroupPost/{memberId}")
    public ResponseEntity myGroupPost(@PathVariable Long memberId){
        List<Posting> posts = myPageService.getMyPost(memberId);
        return ResponseEntity.ok(posts);

    }

    /**
     * edit. 22.07.20
     * 북마크 조회
     */
    @GetMapping("/mypage/bookmark/{memberId}")
    public ResponseEntity<Member> findBookMark(@PathVariable Long memberId) {

        Member member = memberRepository.findById(memberId).get();
        List<Bookmark> bookmarks = bookmarkRepository.findAllByMember(member);

        List<Posting> bookMarkList = new ArrayList<>();

        for(Bookmark bookmark:  bookmarks) {
            bookMarkList.add(bookmark.getPosting());
        }

        return new ResponseEntity(bookMarkList,HttpStatus.OK);
    }


    @DeleteMapping("/mypage/{bookmarkId}")
    public Long deleteBookmark(@PathVariable Long memberId) {
        return memberService.deleteAccount(memberId);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler (UserException e) {
        ErrorResult errorResult = new ErrorResult("사용자를 찾지 못하였습니다.",e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

}



