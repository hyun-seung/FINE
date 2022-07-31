package com.fine_server.service.posting;

import com.fine_server.entity.*;
import com.fine_server.entity.bookmark.GetBookmarkDto;
import com.fine_server.entity.posting.*;
import com.fine_server.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.07.31
 * LastModifiedPerson : eunhye
 */

@Service
@RequiredArgsConstructor
@Transactional
public class PostingService {

    private final PostingRepository postingRepository;
    private final MemberRepository memberRepository;
    private final RecruitingRepository recruitingRepository;
    private final GroupCollectionRepository groupCollectionRepository;
    private final BookmarkRepository bookmarkRepository;
    private final GroupService groupService;


    // 포스팅 만들기
    public Posting make(Long memberId, PostingCreateDto postingCreateDto) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();
        Posting save = postingRepository.save(postingCreateDto.toEntity());
        save.setMember(member);
        return save;
    }

    // 포스팅 수정(내용만)
    public Posting updateText(Long postingId, TextDto textDto) {
        Optional<Posting> optionalPosting = postingRepository.findById(postingId);
        Posting posting = optionalPosting.get();
        posting.setText(textDto);
        return posting;
    }

    // 포스팅 수정(마감 여부 반대로 변경)
    public Posting updateClosingCheck(Long postingId) {
        Optional<Posting> optionalPosting = postingRepository.findById(postingId);
        Posting posting = optionalPosting.get();
        posting.updateClosingCheck(!posting.getClosing_check());
        return posting;
    }

    // 해당 포스팅 불러오기
    @Transactional(readOnly = true)
    public GetPostingDto findPosting(Long postingId, Long memberId) {
        Optional<Posting> optionalPosting = postingRepository.findById(postingId);
        Posting posting = optionalPosting.get();

        List<Recruiting> recruitingList = posting.getRecruitingList();
        List<RecruitingDto> newRecruiting = new ArrayList<>();
        for(Recruiting recruiting: recruitingList) {
            RecruitingDto recruitingDto = new RecruitingDto(recruiting.getAccept_check(),
                    new GetMemberDto(recruiting.getMember().getId(), recruiting.getMember().getNickname(), recruiting.getMember().getLevel()));
            newRecruiting.add(recruitingDto);
        }

        List<Comment> commentList = posting.getComments();
        List<CommentMemberDto> newCommentList = new ArrayList<>();
        for(Comment comment : commentList) {
            CommentMemberDto commentMemberDto = new CommentMemberDto(comment.getId(), comment.getText(),
                    new GetMemberDto(comment.getMember().getId(), comment.getMember().getNickname(), comment.getMember().getLevel()));
            newCommentList.add(commentMemberDto);
        }

        List<Bookmark> BookmarkList = bookmarkRepository.findAllByPosting(posting);
        List<GetBookmarkDto> newBookmarkList = new ArrayList<>();
        for(Bookmark bookmark : BookmarkList) {
            GetBookmarkDto getBookmarkDto = new GetBookmarkDto(bookmark.getId(), bookmark.getMember().getId());
            newBookmarkList.add(getBookmarkDto);
        }

        GetPostingDto postingDto = new GetPostingDto(posting.getId(), posting.getMember().getId(),
                posting.getMember().getNickname(), posting.getTitle(), posting.getContent(),
                posting.getClosing_check(), posting.getGroup_check(), posting.getMaxMember(),
                headCount(posting.getId()), joinCheck(postingId, memberId),
                posting.getCreatedDate(), posting.getLastModifiedDate(),
                newRecruiting, newCommentList, newBookmarkList);
        return postingDto;
    }

    // 일반 포스팅 전체 불러오기
    @Transactional(readOnly = true)
    public List<FindGeneralPostingDto> findGeneralPostings(Boolean groupCheck) {
        List<Posting> postings = postingRepository.findPostings(groupCheck);
        List<FindGeneralPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindGeneralPostingDto findGeneralPostingDto = new FindGeneralPostingDto(
                    posting.getId(), posting.getMember().getId(), posting.getMember().getNickname(),
                    posting.getTitle(), posting.getContent(), posting.getComments().size(),
                    posting.getCreatedDate(), posting.getLastModifiedDate(), posting.getClosing_check()
            );
            postingDtos.add(findGeneralPostingDto);
        }
        return postingDtos;
    }

    // 그룹 포스팅 전체 불러오기
    @Transactional(readOnly = true)
    public List<FindGroupPostingDto> findGroupPostings(Boolean groupCheck) {
        List<Posting> postings = postingRepository.findPostings(groupCheck);
        List<FindGroupPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindGroupPostingDto findGroupPostingDto = new FindGroupPostingDto(
                    posting.getId(), posting.getMember().getId(), posting.getMember().getNickname(), posting.getTitle(), posting.getContent(),
                    posting.getCreatedDate(), posting.getLastModifiedDate(), posting.getMaxMember(), headCount(posting.getId()), posting.getClosing_check()
            );
            postingDtos.add(findGroupPostingDto);
        }
        return postingDtos;
    }


    // 그룹 포스팅 모집 여부에 따라 불러오기
    @Transactional(readOnly = true)
    public List<FindGroupPostingDto> findGroupClosingPostings(Boolean closingCheck) {
        List<Posting> postings = postingRepository.findGroupClosingPosting(closingCheck);
        List<FindGroupPostingDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindGroupPostingDto findGroupPostingDto = new FindGroupPostingDto(
                    posting.getId(), posting.getMember().getId(), posting.getMember().getNickname(), posting.getTitle(), posting.getContent(),
                    posting.getCreatedDate(), posting.getLastModifiedDate(), posting.getMaxMember(), headCount(posting.getId()), posting.getClosing_check()
            );
            postingDtos.add(findGroupPostingDto);
        }
        return postingDtos;
    }

    public Long deleteById(Long postingId) {
        postingRepository.deleteById(postingId);
        return postingId;
    }

    //참여하기
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

    //참여하기 취소 (삭제)
    public Long deleteRecruiting(Long recruitingId) {
        recruitingRepository.deleteById(recruitingId);
        return recruitingId;
    }

    // 참여 수락 및 수락 취소
    public Recruiting joinAccept(Long postingId, Long recruitingId, RecruitingDto recruitingDto) {
        Optional<Recruiting> recruiting = recruitingRepository.findById(recruitingId);
        Recruiting save = recruiting.get();
        save.updateAcceptCheck(recruitingDto.getAccept_check());

        Optional<Posting> posting = postingRepository.findById(postingId);
        // 현재 수락 인원이 max면 포스팅 마감 결정
        if(headCount(postingId) == posting.get().getMaxMember()) {
            posting.get().updateClosingCheck(true);

            groupService.makeGroup(postingId);
        }
        return save;
    }

    //참여 여부 체크
    public Boolean joinCheck(Long postingId, Long memberId) {
        List<Recruiting> recruitingList = recruitingRepository.findByPostingId(postingId);

        for(Recruiting recruiting : recruitingList) {
            if (recruiting.getMember().getId().equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    // 현재 수락 인원 체크
    public Integer headCount(Long postingId) {
        List<Recruiting> recruitingList = recruitingRepository.findByPostingId(postingId);

        int count = 0;
        for(Recruiting recruiting : recruitingList) {
            if(recruiting.getAccept_check().equals(true)) {
                count++;
            }
        }
        return count;
    }

    // 글 제목으로 검색
    @Transactional (readOnly = true)
    public List<FindPostingsDto> findSearchPostings(String title) {
        List<Posting> postings = postingRepository.findByTitleContaining(title);
        List<FindPostingsDto> postingDtos = new ArrayList<>();
        for(Posting posting : postings) {
            FindPostingsDto findPostingsDto = new FindPostingsDto(
                    posting.getId(), posting.getMember().getId(), posting.getTitle(), posting.getGroup_check(),
                    posting.getComments().size(), posting.getCreatedDate(), posting.getLastModifiedDate(), posting.getClosing_check()
            );
            postingDtos.add(findPostingsDto);
        }
        return postingDtos;
    }

    // 조회수 초기화
    @Transactional (readOnly = true)
    public void initViews() {
        List<Posting> postingList = postingRepository.findAll();
    }
}