package com.fine_server.repository;

import com.fine_server.entity.Keyword;
import com.fine_server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * written by dahae
 * date: 22.06.26
 */
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    /**
     * 사용자 키워드 조회
     */
    @Query("select k from Keyword k where k.member = :member")
    List<Keyword> findAllByMember(@Param("member") Member member);
}
