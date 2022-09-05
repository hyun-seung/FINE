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

    @Query("select m from Member m where m.id = :memberId")
    List<Member> findTop20ByOrderByMemberId();

    List<Member> findByKeyword1(String keyword);
    List<Member> findByKeyword2(String keyword);
}
