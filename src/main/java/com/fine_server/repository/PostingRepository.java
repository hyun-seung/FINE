package com.fine_server.repository;

import com.fine_server.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * written by eunhye
 * date: 22.06.16
 */

public interface PostingRepository extends JpaRepository<Posting, Long> {

    public List<Posting> findByGroupCheck(Boolean groupCheck);


}
