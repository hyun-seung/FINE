package com.fine_server.controller.Posting;

import com.fine_server.entity.Group;
import com.fine_server.entity.Posting;
import com.fine_server.service.posting.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/group/{postingId}")
    public Group createGroup(@PathVariable Long postingId) {
        Group save = groupService.makeGroup(postingId);
        return save;
    }
}
