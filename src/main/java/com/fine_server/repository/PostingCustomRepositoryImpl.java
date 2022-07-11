package com.fine_server.repository;

import com.fine_server.entity.Posting;
import com.fine_server.entity.Recruiting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class PostingCustomRepositoryImpl implements PostingCustomRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Posting> findGeneralPosting() {
        return em.createQuery("select p from Posting p where p.group_check = false")
                .getResultList();
    }

    // 그룹 전체
    @Override
    public List<Posting> findGroupPosting() {
        return em.createQuery("select p from Posting p where p.group_check = true")
                .getResultList();
    }

    // 모집 중 (closing X)
    @Override
    public List<Posting> findGroupClosingFPosting() {
        return em.createQuery("select p from Posting p where p.group_check = true and p.closing_check = false")
                .getResultList();
    }


    // 모집 완료 (closing O)
    @Override
    public List<Posting> findGroupClosingTPosting() {
        return em.createQuery("select p from Posting p where p.group_check = true and p.closing_check = true")
                .getResultList();
    }

    @Override
    public List<Recruiting> findAcceptCheckT(Long postingId) {
        return em.createQuery("select r from Recruiting r where r.accept_check=true and r.posting.id = :postingId")
                .setParameter("postingId", postingId)
                .getResultList();
    }


}
