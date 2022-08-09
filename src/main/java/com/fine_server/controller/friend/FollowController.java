package com.fine_server.controller.friend;

import com.fine_server.entity.Follow;
import com.fine_server.entity.FollowDto;
import com.fine_server.entity.Member;
import com.fine_server.service.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * written by eunhye
 * date: 22.08.09
 * LastModifiedPerson : eunhye
 */


@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 친구 팔로잉
    @PostMapping("/follow/{friendId}/{memberId}")
    public Follow createFollow(@PathVariable Long friendId, @PathVariable Long memberId) {
        Follow save = followService.make(friendId, memberId);
        return save;
    }

    // 팔로우 취소
    @DeleteMapping("follow/{followId}/delete")
    public Long deleteFollow(@PathVariable Long followId) {
        Long deleteFollowId = followService.deleteById(followId);
        return deleteFollowId;
    }

    // 팔로우 목록 불러오기
    @GetMapping("/followList/{memberId}")
    public List<FollowDto> getFollowList(@PathVariable Long memberId) {
        List<FollowDto> followList = followService.getFollowList(memberId);
        return followList;
    }

    // 팔로우 목록에서 검색 - 닉네임만 됨, 키워드 추후 수정
    @GetMapping("followList/search/{memberId}")
    public List<Member> searchFollow(@RequestParam String search, @PathVariable Long memberId) {
        List<Member> followDtoList = followService.searchFollow(memberId, search);
        return followDtoList;
    }
}
