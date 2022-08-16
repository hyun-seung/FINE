package com.fine_server.service.mypage;

import com.fine_server.entity.Posting;
import com.fine_server.repository.FollowRepository;
import com.fine_server.repository.PostingRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<Posting> Postings = postingRepository.findAll();
        List<Posting> myPosting = new ArrayList<>();

        for(Posting post : Postings){
            if(post.getMember().getId() == memberId)
                myPosting.add(post);
        }
        return myPosting;
    }
}
