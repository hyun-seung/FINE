package com.fine_server.controller.friend;

import com.fine_server.entity.keyword.KeywordRequestDto;
import com.fine_server.entity.mypage.MemberResponseDto;
import com.fine_server.service.mypage.KeywordService;
import com.fine_server.service.mypage.MemberService;
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
    private final MemberService memberService;

    /* 키워드 카테고리별 친구 목록 */
    @GetMapping("/recommend/{memberId}")
    List<MemberResponseDto> recommendList(@PathVariable Long memberId, @RequestParam Integer category) {
        return keywordService.keywordOfCategory(memberId, category);
    }

//    @GetMapping("/recommend/all")
//    List<MemberResponseDto> recommendAll() {
//        return keywordService.recommendAll();
//    }

    /* 키워드 편집 (생성, 변경) */
    @PostMapping("/keyword/edit/{memberId}")
    String editKeyword(@PathVariable Long memberId, @RequestBody KeywordRequestDto keywordRequestDto) {
        return keywordService.editKeyword(memberId, keywordRequestDto);
    }


}
