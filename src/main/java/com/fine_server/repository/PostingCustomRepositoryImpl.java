package com.fine_server.repository;

import com.fine_server.entity.Posting;
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

    @Override
    public List<Posting> findGroupPosting() {
        return em.createQuery("select p from Posting p where p.group_check = false")
                .getResultList();
    }
}
