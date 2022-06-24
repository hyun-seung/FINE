package com.fine_server.repository;

import com.fine_server.entity.Posting;

import java.util.List;

public interface PostingCustomRepository {

    List<Posting> findGeneralPosting();

    List<Posting> findGroupPosting();

}
