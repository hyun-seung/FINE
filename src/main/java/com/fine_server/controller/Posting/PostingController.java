package com.fine_server.controller.Posting;

import com.fine_server.service.Posting.PostingService;
import com.fine_server.entity.Posting;
import com.fine_server.entity.posting.FindAllPostingDto;
import com.fine_server.entity.posting.PostingCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * written by hyunseung
 * date: 22.05.28
 */


@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    // 목록 조회
    @GetMapping("/posting")
    public List<FindAllPostingDto> getPostings() {
        List<FindAllPostingDto> postings = postingService.findPostings();
        return postings;
    }

    // 글 한 개 조회
    @GetMapping("/posting/{postingId}")
    public Posting getPosting(@PathVariable Long postingId) {
        Posting posting = postingService.findPosting(postingId);
        return posting;
    }

    // 글 작성
    @PostMapping("/posting/{memberId}")
    public Posting createPosting(@PathVariable Long memberId, @RequestBody PostingCreateDto postingCreateDto) {
        Posting save = postingService.make(postingCreateDto, memberId);
        return save;
    }

    /**
     * written by eunhye
     * date: 22.06.11
     */

    @GetMapping("/posting/group")
    public List<FindAllPostingDto> getGroupPostings() {
        List<FindAllPostingDto> postings = postingService.findGroupPostings();
        return postings;
    }

    @GetMapping("/posting/{postingId}")
    public Optional<Posting> getPosting(@PathVariable Long postingId) {
        Optional<Posting> posting = postingService.findOne(postingId);
        return posting;
    }

}
