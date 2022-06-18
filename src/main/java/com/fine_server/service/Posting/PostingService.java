package com.fine_server.service.Posting;


import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import com.fine_server.entity.posting.FindAllPostingDto;
import com.fine_server.entity.posting.PostingCreateDto;
import com.fine_server.repository.MemberRepository;
import com.fine_server.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * written by hyunseung
 * date: 22.05.28
 */

@Service
@RequiredArgsConstructor
@Transactional
public class PostingService {

    private final PostingRepository postingRepository;
    private final MemberRepository memberRepository;

    // 포스팅 만들기
    public Posting make(PostingCreateDto postingCreateDto, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();
        Posting save = postingRepository.save(postingCreateDto.toEntity());
        save.setMember(member);
        return save;
    }

    // 포스팅 전체 불러오기
    @Transactional(readOnly = true)
    public List<FindAllPostingDto> findPostings() {
        List<Posting> postings = postingRepository.findAll();
        List<FindAllPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindAllPostingDto findAllPostingDto = new FindAllPostingDto(
                    posting.getId(), posting.getMember().getId(), posting.getTitle(), posting.getContent(), posting.getEndTime(),
                    posting.getCreatedDate(), posting.getLastModifiedDate()
            );
            postingDtos.add(findAllPostingDto);
        }
        return postingDtos;
    }

    /**
     * written by eunhye
     * date: 22.06.11
     */

    // 그룹 포스팅 전체 불러오기
    @Transactional(readOnly = true)
    public List<FindAllPostingDto> findGroupPostings() {
        List<Posting> postings = postingRepository.findByGroupCheck(true);
        List<FindAllPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindAllPostingDto findAllPostingDto = new FindAllPostingDto(
                    posting.getId(), posting.getMember().getId(), posting.getTitle(), posting.getContent(), posting.getEndTime(),
                    posting.getCreatedDate(), posting.getLastModifiedDate(),
                    posting.getMaxMember(), posting.getClosing_check()
            );
            postingDtos.add(findAllPostingDto);
        }
        return postingDtos;
    }

    // 해당 포스팅 불러오기
    public Optional<Posting> findOne(Long postingId) {
        return postingRepository.findById(postingId);
    }

}
