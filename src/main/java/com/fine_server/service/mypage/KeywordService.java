package com.fine_server.service.mypage;

import com.fine_server.entity.Keyword;
import com.fine_server.entity.Member;
import com.fine_server.entity.keyword.KeywordRequestDto;
import com.fine_server.entity.posting.GetMemberDto;
import com.fine_server.repository.KeywordRepository;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * written by eunhye
 * date: 22.09.07
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final MemberRepository memberRepository;

    public List<Keyword> findByMemberId(Member member) {
        return keywordRepository.findAllByMember(member);
    }

    
    //키워드 카테고리별 일치 멤버 리스트 - 자신 제외
    public List<GetMemberDto> keywordOfCategory(Long memberId, Integer category) {
        Member member = memberRepository.findById(memberId).get();
        List<Member> memberList = null;
        if (category.equals(1)) { // 전공
            memberList = memberRepository.findByKeyword1(member.getKeyword1(), memberId);
        }
        else if (category.equals(2)) { //거주지
            memberList = memberRepository.findByKeyword2(member.getKeyword2(), memberId);
        }
        else if (category.equals(4)) { // 모든 멤버
             memberList = memberRepository.findTop20ByOrderByMemberId();
        }
        else { //전부 일치
            memberList = memberRepository.findByKeyword1AndKeyword2(member.getKeyword1(), member.getKeyword2(), memberId);
        }

        List<GetMemberDto> memberResponseDtoList = new ArrayList<>();
        for (Member members: memberList) {
            GetMemberDto responseDto = members.getMemberInfo();
            memberResponseDtoList.add(responseDto);
        }

        return memberResponseDtoList;
    }

    //키워드 편집 - 전공
    public String editKeyword(Long memberId, KeywordRequestDto keyword) {
        Member member = memberRepository.findById(memberId).get();
        return member.editKeyword1(keyword);
    }

}
