package com.fine_server.controller.mypage;

import com.fine_server.controller.mypage.errors.UserException;
import com.fine_server.entity.*;
import com.fine_server.controller.signup.dto.SignupResponseDto;
import com.fine_server.repository.BookmarkRepository;
import com.fine_server.repository.FollowRepository;
import com.fine_server.repository.MemberRepository;
import com.fine_server.service.mypage.MemberService;
import com.fine_server.controller.mypage.errors.ErrorResult;
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
    private final FollowRepository followRepository;
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
     *
     * edit. 22.08.20
     * 수정 이유 : Member Entity 정규화
     *           인증 날짜 정보 추가
     */
    @ResponseBody
    @GetMapping("/mypage/{memberId}")
    public ResponseEntity<Member> myProfile(@PathVariable Long memberId){
        Optional<Member> findMember = memberService.findMember(memberId);

        if (!findMember.isEmpty()){
            Member member = findMember.get();
            MemberDetail memberDetail = member.getMemberDetail();
            List<Follow> followList = followRepository.findFriends(memberId);
            
            //사용하는 정보가 같아 SignupResponseDto 사용
            SignupResponseDto signupResponseDto = new SignupResponseDto(member.getNickname(),member.getUserImageNum(),member.getIntro(), member.getKeyword1(), member.getKeyword2(),member.getKeyword3(),followList.size(),memberDetail.getEmail(),memberDetail.getUpdateDateEmail(),memberDetail.getUserPhoneNumber(),memberDetail.getUpdateDatePhone(),memberDetail.getUserResidence(),memberDetail.getUpdateDateResidence());
            return new ResponseEntity(signupResponseDto, HttpStatus.OK);
        }

        else{
            throw new UserException("사용자를 찾지 못하였습니다.");
        }
    }

    /**
     * edit. 22.06.26
     */
    @PostMapping("/mypage/profile/{memberId}")
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
     * edit. 22.08.20
     * 친구 프로필 조회
     * 수정 이유 : Member Entity 정규화
     *           인증 날짜 정보 추가
     */

    @GetMapping("/profile/{memberId}")
    public ResponseEntity<Member> profile(@PathVariable Long memberId){
        Optional<Member> findMember = memberService.findMember(memberId);

        if (!findMember.isEmpty()){
            Member member = findMember.get();
            MemberDetail memberDetail = member.getMemberDetail();
            List<Follow> followList = followRepository.findFriends(memberId);

            //사용하는 정보가 같아 SignupResponseDto 사용
            SignupResponseDto signupResponseDto = new SignupResponseDto(member.getNickname(),member.getUserImageNum(),member.getIntro(), member.getKeyword1(), member.getKeyword2(),member.getKeyword3(),followList.size(),memberDetail.getEmail(),memberDetail.getUpdateDateEmail(),memberDetail.getUserPhoneNumber(),memberDetail.getUpdateDatePhone(),memberDetail.getUserResidence(),memberDetail.getUpdateDateResidence());
            return new ResponseEntity(signupResponseDto, HttpStatus.OK);
        }

        else{
            throw new UserException("사용자를 찾지 못하였습니다.");
        }
    }

    /**
     * add. 22.06.23
     * 내 게시글 조회
     */
    @GetMapping("/mypage/post/{memberId}")
    public ResponseEntity myPost(@PathVariable Long memberId){
        List<Posting> posts = myPageService.getMyPost(memberId);
        return ResponseEntity.ok(posts);
    }

    /**
     * add. 22.07.27
     * 내 그룹 신청글 조회
     */
    @GetMapping("/mypage/post/group/{memberId}")
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

    /**
     * edit. 22.07.19
     * 멤버 탈퇴
     */
    @DeleteMapping("/mypage/{memberId}")
    public Long deleteMember(@PathVariable Long memberId) {
        return memberService.deleteAccount(memberId);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler (UserException e) {
        ErrorResult errorResult = new ErrorResult("사용자를 찾지 못하였습니다.",e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

}



