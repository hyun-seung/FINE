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
 * LastModifiedDate: 22.08.15
 * LastModifiedPerson : hyunseung
 */

@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    // 해당 글 조회 (개인 , 그룹 상관 없이)
    @GetMapping("/post/{postingId}/{memberId}")
    public GetPostingDto getPosting(@PathVariable Long postingId, @PathVariable Long memberId) {
        GetPostingDto postingDto = postingService.findPosting(postingId, memberId);
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
    public List<FindPostingsDto> getGeneralPostings() {
        List<FindPostingsDto> postings = postingService.findGroupPostings(false);
        return postings;
    }

    // 단체 글 목록 조회
    @GetMapping("/post/group")
    public List<FindPostingsDto> getGroupPostings() {
        List<FindPostingsDto> postings = postingService.findGroupPostings(true);
        return postings;
    }

    // 단체 글 진행 목록 조회
    @GetMapping("/post/group/proceed")
    public List<FindPostingsDto> getGroupProceedPostings() {
        List<FindPostingsDto> postings = postingService.findGroupClosingPostings(false);
        return postings;
    }

    // 단체 글 마감 목록 조회
    @GetMapping("/post/group/close")
    public List<FindPostingsDto> getGroupClosingPostings() {
        List<FindPostingsDto> postings = postingService.findGroupClosingPostings(true);
        return postings;
    }

    // 참여하기
    @PostMapping("/post/{postingId}/{memberId}/join")
    public ResponseEntity<Recruiting> groupJoin(@RequestBody RecruitingDto recruitingDto, @PathVariable Long postingId, @PathVariable Long memberId) {
        Recruiting recruiting = postingService.groupJoin(postingId, memberId, recruitingDto);
        return new ResponseEntity(recruiting, HttpStatus.OK);
    }

    // 참여하기 취소 (삭제)
    @DeleteMapping("/post/delete/{recruitingId}")
    public Long deleteRecruiting(@PathVariable Long recruitingId) {
        return postingService.deleteRecruiting(recruitingId);
    }

    // 검색
    @GetMapping("post/search")
    public List<FindPostingsDto> getSearchPostings(@RequestParam String title) {
        List<FindPostingsDto> postings = postingService.findSearchPostings(title);
        return postings;
    }

    // 메인 - 인기글 5개
    @GetMapping("/main/popular")
    public List<FindPostingsDto> popularPostings() {
        List<FindPostingsDto> popularPostings = postingService.popularPostings();
        return popularPostings;
    }

}