package com.fine_server.repository;
import com.fine_server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query("select m from Member m order by m.level")
    List<Member> findTop20ByOrderByLevel();


    @Query("select m from Member m where m.id not in :memberId and m.keyword1 = :keyword1 order by m.level desc")
    List<Member> findByKeyword1OrderByLevel(String keyword1, Long memberId);

    @Query("select m from Member m where m.id not in :memberId and m.keyword1 = :keyword1 order by m.id desc")
    List<Member> findByKeyword1OrderByMemberId(String keyword1, Long memberId);


    @Query("select m from Member m where m.id not in :memberId and m.keyword2 = :keyword2 order by m.level desc")
    List<Member> findByKeyword2OrderByLevel(String keyword2, Long memberId);

    @Query("select m from Member m where m.id not in :memberId and m.keyword2 = :keyword2 order by m.id desc")
    List<Member> findByKeyword2OrderByMemberId(String keyword2, Long memberId);


    @Query("select m from Member m where m.id not in :memberId and m.keyword1 = :keyword1 and m.keyword2 = :keyword2")
    List<Member> findByKeyword1AndKeyword2(String keyword1, String keyword2, Long memberId);


    @Query("select m from Member m where m.id not in :memberId and m.keyword1 = :keyword1 and m.keyword2 = :keyword2 order by m.level desc")
    List<Member> findByKeyword1AndKeyword2OrderByLevel(String keyword1, String keyword2, Long memberId);


    boolean existsByNickname(String nickname);
    boolean existsByUserId(String userId);
}
