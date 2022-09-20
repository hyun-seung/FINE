package com.fine_server.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class KeywordCustomRepositoryImpl implements KeywordCustomRepository{

    @Autowired
    EntityManager em;

    @Override
    public String findByMemberIdAndType(Long memberId, Integer type) {
        return em.createQuery("select k.keyword from Keyword k where k.member.id= :memberId and k.type= :type")
                .setParameter("memberId", memberId)
                .setParameter("type", type)
                .getSingleResult().toString();
    }
}
