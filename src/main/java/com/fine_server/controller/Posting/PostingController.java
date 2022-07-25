package com.fine_server.controller.Posting;

import com.fine_server.entity.Posting;
import com.fine_server.entity.Recruiting;
import com.fine_server.entity.posting.*;
import com.fine_server.service.posting.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.30
 * LastModifiedPerson : eunhye
 */

@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    // 해당 글 조회 (개인 , 그룹 상관 없이)
    @GetMapping("/post/{postingId}")
    public GetPostingDto getPosting(@PathVariable Long postingId) {
        GetPostingDto postingDto = postingService.findPosting(postingId);
        return postingDto;
    }

    // 글 작성
    @PostMapping("/post/{memberId}")
    public Posting createPosting(@PathVariable Long memberId, @RequestBody PostingCreateDto postingCreateDto) {
        Posting save = postingService.make(memberId, postingCreateDto);
        return save;
    }

    // 해당 글 삭제
    @DeleteMapping("/post/{postingId}")
    public Long deletePosting(@PathVariable Long postingId) {
        Long deletePostingId = postingService.deleteById(postingId);
        return deletePostingId;
    }

    // 해당 글 내용 수정
    @PutMapping("/post/text/{postingId}")
    public Posting updatePostingText(@PathVariable Long postingId, @RequestBody TextDto textDto) {
        Posting posting = postingService.updateText(postingId, textDto);
        return posting;
    }

    // 해당 글 마감 여부 변경
    @PutMapping("/post/close/{postingId}")
    public Posting updatePostingClosingCheck(@PathVariable Long postingId) {
        Posting posting = postingService.updateClosingCheck(postingId);
        return posting;
    }

    // 일반 글 목록 조회
    @GetMapping("/post/general")
    public List<FindGeneralPostingDto> getGenralPostings() {
        List<FindGeneralPostingDto> postings = postingService.findGeneralPostings(false);
        return postings;
    }

    // 단체 글 목록 조회
    @GetMapping("/post/group")
    public List<FindGroupPostingDto> getGroupPostings() {
        List<FindGroupPostingDto> postings = postingService.findGroupPostings(true);
        return postings;
    }

    // 단체 글 진행 목록 조회
    @GetMapping("/post/group/proceed")
    public List<FindGroupPostingDto> getGroupProceedPostings() {
        List<FindGroupPostingDto> postings = postingService.findGroupClosingPostings(false);
        return postings;
    }

    // 단체 글 마감 목록 조회
    @GetMapping("/post/group/close")
    public List<FindGroupPostingDto> getGroupClosingPostings() {
        List<FindGroupPostingDto> postings = postingService.findGroupClosingPostings(true);
        return postings;
    }

    // 참여하기 - memberId dto에 담아서 하는 걸로 리팩토링
    @PostMapping("/post/{postingId}/{memberId}/join")
    public ResponseEntity<Recruiting> groupJoin(@RequestBody RecruitingDto recruitingDto, @PathVariable Long postingId, @PathVariable Long memberId) {
        Recruiting recruiting = postingService.groupJoin(postingId, memberId, recruitingDto);
        return new ResponseEntity(recruiting, HttpStatus.OK);
    }

    // 참여하기 취소 (삭제)
    @DeleteMapping("/post/{recruitingId}/delete")
    public Long deleteRecruiting(@PathVariable Long recruitingId) {
        return postingService.deleteRecruiting(recruitingId);
    }

    // 검색
    @GetMapping("post/search")
    public List<FindPostingsDto> getSearchPostings(@RequestParam String title) {
        List<FindPostingsDto> postings = postingService.findSearchPostings(title);
        return postings;
    }

}