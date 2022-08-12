package com.fine_server.repository;

import com.fine_server.entity.RoomCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCollectionRepository extends JpaRepository<RoomCollection, Long> {
}
