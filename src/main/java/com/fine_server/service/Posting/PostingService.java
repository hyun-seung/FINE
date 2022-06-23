package com.fine_server.Service.Posting;

import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import com.fine_server.entity.posting.FindAllPostingDto;
import com.fine_server.entity.posting.FindGeneralPostingDto;
import com.fine_server.entity.posting.FindGroupPostingDto;
import com.fine_server.entity.posting.PostingCreateDto;
import com.fine_server.repository.MemberRepository;
import com.fine_server.repository.PostingRepository_Detail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.20
 * LastModifiedPerson : hyunseung
 */

@Service
@RequiredArgsConstructor
@Transactional
public class PostingService {

    private final PostingRepository_Detail postingRepository;
    private final MemberRepository memberRepository;

    /*
        메모장 : 해당 글의 마감여부만 변경해주는게 필요하다.
     */

    // 포스팅 만들기
    public Posting make(PostingCreateDto postingCreateDto, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();
        Posting save = postingRepository.save(postingCreateDto.toEntity());
        save.setMember(member);
        return save;
    }

    // 해당 포스팅 불러오기
    @Transactional(readOnly = true)
    public Posting findPosting(Long postingId) {
        Posting posting = postingRepository.findById(postingId);
        return posting;
    }

    // 포스팅 전체(일반, 그룹 모두) 불러오기
    @Transactional(readOnly = true)
    public List<FindAllPostingDto> findAllPostings() {
        List<Posting> postings = postingRepository.findAll();
        List<FindAllPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindAllPostingDto findAllPostingDto = new FindAllPostingDto(
                    posting.getId(), posting.getMember().getId(), posting.getTitle(), posting.getContent(), posting.getEndTime(),
                    posting.getCreatedDate(), posting.getLastModifiedDate(), posting.getMaxMember(), posting.getClosing_check(), posting.getGroup_check()
            );
            postingDtos.add(findAllPostingDto);
        }
        return postingDtos;
    }

    // 일반 포스팅 전체 불러오기
    @Transactional(readOnly = true)
    public List<FindGeneralPostingDto> findGeneralPostings() {
        List<Posting> postings = postingRepository.findGeneralPosting();
        List<FindGeneralPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindGeneralPostingDto findGeneralPostingDto = new FindGeneralPostingDto(
                    posting.getId(), posting.getMember().getId(), posting.getTitle(), posting.getContent(), posting.getEndTime(),
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
                    posting.getId(), posting.getMember().getId(), posting.getTitle(), posting.getContent(), posting.getEndTime(),
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
}