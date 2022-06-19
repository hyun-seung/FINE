package com.fine_server.Controller.Posting;

import com.fine_server.Service.Posting.PostingService;
import com.fine_server.entity.Posting;
import com.fine_server.entity.posting.FindAllPostingDto;
import com.fine_server.entity.posting.PostingCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
}
