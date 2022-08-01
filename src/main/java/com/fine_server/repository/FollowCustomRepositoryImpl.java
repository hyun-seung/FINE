package com.fine_server.repository;

import com.fine_server.entity.Follow;
import com.fine_server.entity.FollowDto;
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

    //성능 이슈 : count 보단 exists로
    @Override
    public Long findByMemberIdAndFriendId(Long memberId, Long friendId) {
        return (Long) em.createQuery("select count(f) from Follow f where f.member.id = :memberId and f.friend.id = :friendId")
                .setParameter("memberId", memberId)
                .setParameter("friendId", friendId)
                .getSingleResult();
    }

    //키워드 포함으로 수정 필요- 멤버 엔티티에 키워드 리스트 추가
    @Override
    public List<FollowDto> findByNickname(Long memberId, String nickname) {
        return em.createQuery("select f.friend.id, f.friend.nickname, f.friend.intro, f.friend.level " +
                        "from Follow f where f.member.id = :memberId and f.friend.nickname = :nickname")
                .setParameter("memberId", memberId)
                .setParameter("nickname", nickname)
                .getResultList();
    }
}
