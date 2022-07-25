package com.fine_server.repository;

import com.fine_server.entity.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class FollowCustomRepositoryImpl implements FollowCustomRepository{

    @Autowired
    EntityManager em;

    @Override
    public List<Follow> findFriends(Long memberId) {
        return em.createQuery("select f from Follow f where f.member.id = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
