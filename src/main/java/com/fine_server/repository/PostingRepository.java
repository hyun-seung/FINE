package com.fine_server.Repository;

import com.fine_server.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * written by hyunseung
 * date: 22.05.28
 */

public interface PostingRepository extends JpaRepository<Posting, Long> {
}
