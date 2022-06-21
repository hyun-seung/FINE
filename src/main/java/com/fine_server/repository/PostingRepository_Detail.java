package com.fine_server.repository;

import com.fine_server.entity.Posting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.20
 * LastModifiedPerson : hyunseung
 */

@Repository
@RequiredArgsConstructor
public class PostingRepository_Detail {

    private final EntityManager em;

    public Posting save(Posting posting) {
        em.persist(posting);
        return posting;
    }

    public Posting findById(Long postingId) {
        return em.find(Posting.class, postingId);
    }

    public List<Posting> findAll() {
        return em.createQuery("select p from Posting p", Posting.class)
                .getResultList();
    }

    // 여기 2개는 쿼리문 공부할겸 해서 남겨놓을께! 그리고 Boolean형으로 받아서 처리하는 것보다 우리가 먼저 경우의 수를 아니까 이렇게 2개로 분리하는게 좋을 것 같아서 만들어놨어
    public List<Posting> findGeneralPosting() {
        return null;
    }

    public List<Posting> findGroupPosting() {
        return null;
    }

    public void deleteById(Long postingId) {
        Posting posting = findById(postingId);
        if(posting == null) {
            System.out.println("존재하지 않는 포스팅입니다.");
        } else {
            em.remove(posting);
            System.out.println("성공적으로 제거되었습니다.");
        }
    }
}
