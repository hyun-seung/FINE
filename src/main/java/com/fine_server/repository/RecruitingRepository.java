package com.fine_server.repository;
import com.fine_server.entity.Member;
import com.fine_server.entity.Recruiting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitingRepository extends JpaRepository<Recruiting,Long> {

}
