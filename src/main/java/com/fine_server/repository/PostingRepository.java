package com.fine_server.repository;

import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * written by eunhye
 * date: 22.06.16
 */

// findByGroupCheck 같이 query를 쓸려면 Class 를 새로 만들어서 작업해야 되서(PostingRepository_Detail로 만들어놨구) 일단 이거 삭제는 안 하고 사용 안 되게 막아놨어
// 필요없다고 생각하면 그냥 쿨하게 제거해도 상관없어^^ㅎ

/**
 * edit by.dahae
 * date: 22.06.23
 * JPA 리포지토리 사용 필요로 상속 추가
 */
public interface PostingRepository  extends JpaRepository<Posting, Long> {

    //public List<Posting> findByGroupCheck(Boolean groupCheck);

    /**
     * add by.dahae
     * date: 22.06.22
     */
    List<Posting> findByMemberId(Long memberId);
}
