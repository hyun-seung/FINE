package com.fine_server.repository;

import com.fine_server.entity.Posting;
import com.fine_server.entity.Recruiting;

import java.util.List;

public interface PostingCustomRepository {

    List<Posting> findGeneralPosting();

    List<Posting> findGroupPosting();

    List<Posting> findGroupClosingTPosting();

    List<Posting> findGroupClosingFPosting();

    List<Recruiting> findAcceptCheckT(Long postingId);
}
