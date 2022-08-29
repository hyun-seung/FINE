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
import java.util.Optional;

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
        String keyword = keywordRepository.findByMemberIdAndType(memberId, category);

        List<Keyword> memberList = keywordRepository.findAllByKeywordAndType(keyword, category);
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        for (Keyword keywords: memberList) {
            Member member = keywords.getMember();
            MemberResponseDto responseDto = new MemberResponseDto(
                    member.getNickname(), member.getUserImageNum(), member.getIntro(),
                    member.getKeyword1()
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
//
//        List<Keyword> memberList = keywordRepository.findAllByKeywordAndType(keywordList.get(i).getKeyword(), keywordList.get(i).getType());
//        List<Keyword> memberList = keywordRepository.findAllByKeywordAndType(keywordList.get(i).getKeyword(), keywordList.get(i).getType());
//        List<Keyword> memberList = keywordRepository.findAllByKeywordAndType(keywordList.get(i).getKeyword(), keywordList.get(i).getType());
//
//        for (Mem keyword : memberList) {
//            List<Keyword> memberList = keywordRepository.findByMemberIdAndType(member, keyword.getType());
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


    //키워드 변경

//    String updateKeyword(Long memberId, String keyword) {
//        keywordRepository.deleteByMemberId(memberId);
//    }

}
