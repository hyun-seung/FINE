package com.fine_server.repository;

public interface KeywordCustomRepository {

    String findByMemberIdAndType(Long memberId, Integer type);
}
