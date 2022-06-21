package com.fine_server.controller.Posting;

import com.fine_server.Service.Posting.PostingService;
import com.fine_server.entity.Posting;
import com.fine_server.entity.posting.FindAllPostingDto;
import com.fine_server.entity.posting.FindGeneralPostingDto;
import com.fine_server.entity.posting.FindGroupPostingDto;
import com.fine_server.entity.posting.PostingCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.20
 * LastModifiedPerson : hyunseung
 */

@RestController
@RequiredArgsConstructor
public class PostingController {

    /*
        메모장 : 해당 글 수정 필요
     */

    private final PostingService postingService;

    // 글 작성
    @PostMapping("/posting/{memberId}")
    public Posting createPosting(@PathVariable Long memberId, @RequestBody PostingCreateDto postingCreateDto) {
        Posting save = postingService.make(postingCreateDto, memberId);
        return save;
    }

    // 전체(일반 + 단체) 글 조회
    @GetMapping("/posting")
    public List<FindAllPostingDto> getAllPostings() {
        List<FindAllPostingDto> postings = postingService.findAllPostings();
        return postings;
    }

    // 일반 글 목록 조회
    @GetMapping("/posting/general")
    public List<FindGeneralPostingDto> getGenralPostings() {
        List<FindGeneralPostingDto> postings = postingService.findGeneralPostings();
        return postings;
    }

    // 단체 글 목록 조회
    @GetMapping("/posting/group")
    public List<FindGroupPostingDto> getGroupPostings() {
        List<FindGroupPostingDto> postings = postingService.findGroupPostings();
        return postings;
    }

    // 해당 글 조회 (개인 , 그룹 상관 없이)
    @GetMapping("/posting/{postingId}")
    public Posting getPosting(@PathVariable Long postingId) {
        Posting posting = postingService.findPosting(postingId);
        return posting;
    }

    // 해당 글 삭제
    @DeleteMapping("/posting/{postingId}")
    public Long deletePosting(@PathVariable Long postingId) {
        Long deletePostingId = postingService.deleteById(postingId);
        return deletePostingId;
    }
}