package com.fine_server.service.mypage;

import com.fine_server.entity.Keyword;
import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.KeywordDto;
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

    public List<String> save(Member member, List<String> keywordList) {

        List<String> KeywordList = new ArrayList<>();

        for(String keyword: keywordList) {
            KeywordDto keywordDto=new KeywordDto();
            keywordDto.setMember(member);
            keywordDto.setKeyword(keyword);

            keywordRepository.save(keywordDto.toEntity());
            KeywordList.add(keyword);
        }

        return KeywordList;
    }

    public List<Keyword> findByMemberId(Member member) {
        return keywordRepository.findAllByMember(member);
    }

    
    //키워드 카테고리별 일치 멤버 리스트
    public List<MemberResponseDto> keywordOfCategory(String keyword) {
        List<Member> memberList = keywordRepository.findAllByKeyword(keyword);
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>() ;

        for (Member member: memberList) {
            MemberResponseDto responseDto = new MemberResponseDto(
                    member.getNickname(), member.getUserImageNum(), member.getIntro(),
                    member.getKeyword1(), member.getKeyword2(), member.getKeyword3()
            );
            memberResponseDtoList.add(responseDto);
        }

        return memberResponseDtoList;
    }


    //키워드 전부 일치 멤버 리스트
//    public List<MemberResponseDto> keywordAll(String UserId) {
//        List<String> keywordList = new ArrayList<>();
//        keywordList.add(memberRepository.findByUserId(UserId).getKeyword1());
//        keywordList.add(memberRepository.findByUserId(UserId).getKeyword2());
//        keywordList.add(memberRepository.findByUserId(UserId).getKeyword3());
//
//        List<Member> memberList = keywordRepository.findAllByKeyword(keywordList.get(1));
//
//
//        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
//
//        for (Member member: memberList) {
//            MemberResponseDto responseDto = new MemberResponseDto(
//                    member.getNickname(), member.getUserImageNum(), member.getIntro(),
//                    member.getKeyword1(), member.getKeyword2(), member.getKeyword3()
//            );
//            memberResponseDtoList.add(responseDto);
//        }
//
//        return memberResponseDtoList;
//    }

}
