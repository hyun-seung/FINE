package com.fine_server.repository;

import com.fine_server.entity.Posting;
import com.fine_server.entity.Recruiting;

import java.util.List;

public interface PostingCustomRepository {

    List<Posting> findPostings(Boolean groupCheck);

    List<Posting> findGroupClosingPosting(Boolean closingCheck);

    List<Recruiting> findAcceptCheckT(Long postingId);
}
