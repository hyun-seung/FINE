package com.fine_server.service;

import com.fine_server.service.posting.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private PostingService postingService;

    @Scheduled(cron = "0 0 1 * * *") //새벽 1시
    public void initViews() {
        postingService.initViews();
    }
}
