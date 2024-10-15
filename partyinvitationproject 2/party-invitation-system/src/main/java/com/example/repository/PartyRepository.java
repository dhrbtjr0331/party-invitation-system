package com.example.repository;

import com.example.entity.Party;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findByHost(User host);
    List<Party> findByIsPublic(boolean isPublic);
}
