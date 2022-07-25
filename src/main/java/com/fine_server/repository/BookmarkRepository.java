package com.fine_server.repository;

import com.fine_server.entity.Bookmark;
import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    /**
     * edit by dahae
     * date: 22.07.20
     */
    List<Bookmark> findAllByMember(Member member);

    List<Bookmark> findAllByPosting(Posting posting);
}
