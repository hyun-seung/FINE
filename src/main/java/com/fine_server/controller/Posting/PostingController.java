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

    @GetMapping("/posting")
    public List<FindAllPostingDto> getPostings() {
        List<FindAllPostingDto> postings = postingService.findPostings();
        return postings;
    }

    @PostMapping("/posting/{memberId}")
    public Posting createPosting(@PathVariable Long memberId, @RequestBody PostingCreateDto postingCreateDto) {
        Posting save = postingService.make(postingCreateDto, memberId);
        return save;
    }
}
