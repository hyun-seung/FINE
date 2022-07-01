package com.fine_server.controller.Posting;

import com.fine_server.entity.Recruiting;
import com.fine_server.entity.posting.*;
import com.fine_server.entity.Posting;
import com.fine_server.entity.posting.FindGeneralPostingDto;
import com.fine_server.entity.posting.FindGroupPostingDto;
import com.fine_server.entity.posting.PostingCreateDto;
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

    // 글 작성
    @PostMapping("/post/{memberId}")
    public Posting createPosting(@PathVariable Long memberId, @RequestBody PostingCreateDto postingCreateDto) {
        Posting save = postingService.make(memberId, postingCreateDto);
        return save;
    }


    // 일반 글 목록 조회
    @GetMapping("/post/general")
    public List<FindGeneralPostingDto> getGenralPostings() {
        List<FindGeneralPostingDto> postings = postingService.findGeneralPostings();
        return postings;
    }

    // 단체 글 목록 조회
    @GetMapping("/post/group")
    public List<FindGroupPostingDto> getGroupPostings() {
        List<FindGroupPostingDto> postings = postingService.findGroupPostings();
        return postings;
    }

    // 단체 글 진행 목록 조회
    @GetMapping("/post/group/proceed")
    public List<FindGroupPostingDto> getGroupProceedPostings() {
        List<FindGroupPostingDto> postings = postingService.findGroupClosingFPostings();
        return postings;
    }

    // 단체 글 마감 목록 조회
    @GetMapping("/post/group/close")
    public List<FindGroupPostingDto> getGroupClosingPostings() {
        List<FindGroupPostingDto> postings = postingService.findGroupClosingTPostings();
        return postings;
    }

    // 해당 글 조회 (개인 , 그룹 상관 없이)
    @GetMapping("/post/{postingId}")
    public Posting getPosting(@PathVariable Long postingId) {
        Posting posting = postingService.findPosting(postingId);
        return posting;
    }

    // 해당 글 삭제
    @DeleteMapping("/post/{postingId}")
    public Long deletePosting(@PathVariable Long postingId) {
        Long deletePostingId = postingService.deleteById(postingId);
        return deletePostingId;
    }

    // 참여하기
    // DTO로 받으면 참여하기와 참여취소 둘다 한번에 처리가능하도록
    @PostMapping("/post/{postingId}/{memberId}")
    public ResponseEntity<Recruiting> groupJoin(@RequestBody RecruitingDto recruitingDto, @PathVariable Long postingId, @PathVariable Long memberId) {
        Recruiting recruiting = postingService.groupJoin(postingId, memberId, recruitingDto);
        return new ResponseEntity(recruiting, HttpStatus.OK);
    }

}