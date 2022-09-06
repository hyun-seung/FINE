package com.fine_server.repository;
import com.fine_server.entity.Keyword;
import com.fine_server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * written by dahae
 * date: 22.05.27
 *
 * edit by dahae
 * date: 22.08.03
 * 로그인 처리를 위한 커스텀
 */

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByUserId(String userId);

    @Query("select m from Member m order by m.id desc")
    List<Member> findTop20ByOrderByMemberId();

    @Query("select m from Member m where m.id not in :memberId and m.keyword1 = :keyword1")
    List<Member> findByKeyword1(String keyword1, Long memberId);

    @Query("select m from Member m where m.id not in :memberId and m.keyword2 = :keyword2")
    List<Member> findByKeyword2(String keyword2, Long memberId);
}
