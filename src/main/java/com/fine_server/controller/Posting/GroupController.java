package com.fine_server.controller.Posting;

import com.fine_server.entity.Group;
import com.fine_server.entity.Recruiting;
import com.fine_server.entity.posting.RecruitingDto;
import com.fine_server.service.posting.GroupService;
import com.fine_server.service.posting.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final PostingService postingService;

    // 신청 수락 및 수락 취소 (인원 체크 후 마감 여부 자동 변경 포함)
    @PostMapping("/{postingId}/{recruitingId}/accept")
    public ResponseEntity<Recruiting> joinAccept(@RequestBody RecruitingDto recruitingDto, @PathVariable Long postingId, @PathVariable Long recruitingId) {
        Recruiting recruiting = postingService.joinAccept(postingId, recruitingId, recruitingDto);
        return new ResponseEntity(recruiting, HttpStatus.OK);
    }

    @PostMapping("/group/{postingId}")
    public Group createGroup(@PathVariable Long postingId) {
        Group save = groupService.makeGroup(postingId);
        return save;
    }
}
