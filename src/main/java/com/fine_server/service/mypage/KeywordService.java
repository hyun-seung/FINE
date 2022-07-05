package com.fine_server.service.mypage;

import com.fine_server.entity.Keyword;
import com.fine_server.entity.Member;
import com.fine_server.entity.mypage.KeywordDto;
import com.fine_server.repository.KeywordRepository;
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
}
