package com.fine_server.service;

import com.fine_server.service.posting.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final PostingService postingService;

    @Scheduled(cron = "0 5 * * * *") // 매시간 5분
    public void initViews() {
        postingService.initViews();
    }
}
