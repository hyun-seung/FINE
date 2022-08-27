package com.fine_server.controller.friend;

import com.fine_server.entity.mypage.MemberResponseDto;
import com.fine_server.service.mypage.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/keyword/{keyword}")
    List<MemberResponseDto> memberList(@PathVariable String keyword) {
        return keywordService.keywordOfCategory(keyword);
    }

}
