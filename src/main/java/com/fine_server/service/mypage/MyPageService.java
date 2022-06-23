package com.fine_server.service.mypage;

import com.fine_server.entity.Posting;
import com.fine_server.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * written by dahae
 * date: 22.06.22
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MyPageService {
    private final PostingRepository postingRepository;

    public List<Posting> getMyPost(Long memberId) {
        List<Posting> memberPosts = postingRepository.findByMemberId(memberId);
        return memberPosts;
    }
}
