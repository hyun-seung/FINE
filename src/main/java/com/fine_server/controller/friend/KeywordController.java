package com.fine_server.controller.friend;

import com.fine_server.entity.keyword.KeywordRequestDto;
import com.fine_server.entity.mypage.KeywordDto;
import com.fine_server.entity.mypage.MemberResponseDto;
import com.fine_server.service.mypage.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * written by eunhye
 * date: 22.08.24
 * LastModifiedPerson : eunhye
 */

@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    /* 키워드 카테고리별 친구 목록 */
    @GetMapping("/recommend/{memberId}")
    List<MemberResponseDto> memberList(@PathVariable Long memberId, @RequestParam Integer category) {
        return keywordService.keywordOfCategory(memberId, category);
    }

    /* 키워드 변경 */
//    @PostMapping("/keyword/update")
//    String updateKeyword(@RequestBody KeywordRequestDto) {
//        return keywordService.updateKeyword(memberId, keyword);
//    }

}
