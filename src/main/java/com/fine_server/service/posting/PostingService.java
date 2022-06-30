package com.fine_server.service.posting;

import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import com.fine_server.entity.Recruiting;
import com.fine_server.entity.posting.*;
import com.fine_server.repository.MemberRepository;
import com.fine_server.repository.PostingRepository;
import com.fine_server.repository.RecruitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.29
 * LastModifiedPerson : eunhye
 */

@Service
@RequiredArgsConstructor
@Transactional
public class PostingService {

    private final PostingRepository postingRepository;
    private final MemberRepository memberRepository;
    private final RecruitingRepository recruitingRepository;

    /*
        메모장 : 해당 글의 마감여부만 변경해주는게 필요하다.
     */

    // 포스팅 만들기
    public Posting make(Long memberId, PostingCreateDto postingCreateDto) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();
        Posting save = postingRepository.save(postingCreateDto.toEntity());
        save.setMember(member);
        return save;
    }

    // 해당 포스팅 불러오기
    @Transactional(readOnly = true)
    public Posting findPosting(Long postingId) {
        Optional<Posting> posting = postingRepository.findById(postingId);
        return posting.get();
    }

    // 일반 포스팅 전체 불러오기
    @Transactional(readOnly = true)
    public List<FindGeneralPostingDto> findGeneralPostings() {
        List<Posting> postings = postingRepository.findGeneralPosting();
        List<FindGeneralPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindGeneralPostingDto findGeneralPostingDto = new FindGeneralPostingDto(
                    posting.getId(), posting.getMember().getNickname(),
                    posting.getTitle(), posting.getContent(), posting.getComments().size(),
                    posting.getCreatedDate(), posting.getLastModifiedDate(), posting.getClosing_check()
            );
            postingDtos.add(findGeneralPostingDto);
        }
        return postingDtos;
    }

    // 그룹 포스팅 전체 불러오기
    @Transactional(readOnly = true)
    public List<FindGroupPostingDto> findGroupPostings() {
        List<Posting> postings = postingRepository.findGroupPosting();
        List<FindGroupPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindGroupPostingDto findGroupPostingDto = new FindGroupPostingDto(
                    posting.getId(), posting.getMember().getId(), posting.getTitle(), posting.getContent(),
                    posting.getCreatedDate(), posting.getLastModifiedDate(), posting.getMaxMember(), posting.getClosing_check()
            );
            postingDtos.add(findGroupPostingDto);
        }
        return postingDtos;
    }

    public Long deleteById(Long postingId) {
        postingRepository.deleteById(postingId);
        return postingId;
    }

    //참여하기, 취소
    public Recruiting groupJoin(Long postingId, Long memberId, RecruitingDto recruitingDto) {
        Optional<Posting> optionalPosting = postingRepository.findById(postingId);
        Posting posting = optionalPosting.get();
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();

        Recruiting save = recruitingRepository.save(recruitingDto.toEntity());
        save.setPosting(posting);
        save.setMember(member);
        return save;
    }

}