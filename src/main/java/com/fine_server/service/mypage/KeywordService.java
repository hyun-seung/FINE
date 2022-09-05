package com.fine_server.service.mypage;

import com.fine_server.entity.Keyword;
import com.fine_server.entity.Member;
import com.fine_server.entity.keyword.KeywordRequestDto;
import com.fine_server.entity.mypage.MemberResponseDto;
import com.fine_server.repository.KeywordRepository;
import com.fine_server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * written by dahae
 * date: 22.06.26
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final MemberRepository memberRepository;

//    public List<String> save(Member member, List<String> keywordList) {
//
//        List<String> KeywordList = new ArrayList<>();
//
//        for(String keyword: keywordList) {
//            KeywordDto keywordDto=new KeywordDto();
//            keywordDto.setMember(member);
//            keywordDto.setKeyword(keyword);
//
//            keywordRepository.save(keywordDto.toEntity());
//            KeywordList.add(keyword);
//        }
//
//        return KeywordList;
//    }

    public List<Keyword> findByMemberId(Member member) {
        return keywordRepository.findAllByMember(member);
    }

    
    //키워드 카테고리별 일치 멤버 리스트 - 자신 제외 구현 예정
    public List<MemberResponseDto> keywordOfCategory(Long memberId, Integer category) {
        Member member = memberRepository.findById(memberId).get();
        List<Member> memberList = null;
        if (category.equals(3)) { // 전공
            memberList = memberRepository.findByKeyword1(member.getKeyword1());
        }
        else if (category.equals(1)) { //거주지
            memberList = memberRepository.findByKeyword2(member.getKeyword2());
        }
//        else { // 학교
//            memberList = memberRepository.
//        }

        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        for (Member members: memberList) {
            MemberResponseDto responseDto = new MemberResponseDto(
                    members.getNickname(), members.getUserImageNum(), members.getIntro(),
                    members.getKeyword1(), members.getKeyword2(), members.getLevel()
            );
            memberResponseDtoList.add(responseDto);
        }

        return memberResponseDtoList;
    }

    //키워드 상관없이 전부 추천 리스트
    public List<MemberResponseDto> recommendAll() {
        List<Member> memberList = memberRepository.findTop20ByOrderByMemberId();
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        for (Member member: memberList) {
            MemberResponseDto responseDto = new MemberResponseDto(
                    member.getNickname(), member.getUserImageNum(), member.getIntro(),
                    member.getKeyword1(), member.getKeyword2(), member.getLevel()
            );
            memberResponseDtoList.add(responseDto);
        }

        return memberResponseDtoList;
    }


//    키워드 전부 일치 멤버 리스트
//    public List<MemberResponseDto> keywordAll(Long memberId) {
//        Optional<Member> member = memberRepository.findById(memberId);
//        List<Keyword> keywordList = keywordRepository.findAllByMember(member.get()); //사용자의 키워드 리스트
//
//        List<Keyword> memberList = keywordRepository.findAllByKeywordAndType(keywordList.get(i).getKeyword(), keywordList.get(i).getType());
//        List<Keyword> memberList = keywordRepository.findAllByKeywordAndType(keywordList.get(i).getKeyword(), keywordList.get(i).getType());
//        List<Keyword> memberList = keywordRepository.findAllByKeywordAndType(keywordList.get(i).getKeyword(), keywordList.get(i).getType());
//
//        for (Keyword keyword : memberList) {
//            String key = keywordRepository.findByMemberIdAndKeyword(keyword.getMember().getId(), keyword.getType());
//            if (key.equals(keywordList.get(0)))
//
//        }
//
//        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
//
//        for (Member member: memberList) {
//            MemberResponseDto responseDto = new MemberResponseDto(
//                    member.getNickname(), member.getUserImageNum(), member.getIntro(),
//                    member.getKeyword1()
//            );
//            memberResponseDtoList.add(responseDto);
//        }
//
//        return memberResponseDtoList;
//    }



    //키워드 편집 - 전공
    public String editKeyword(Long memberId, KeywordRequestDto keyword) {
        Member member = memberRepository.findById(memberId).get();
        return member.editKeyword1(keyword);
    }

}
