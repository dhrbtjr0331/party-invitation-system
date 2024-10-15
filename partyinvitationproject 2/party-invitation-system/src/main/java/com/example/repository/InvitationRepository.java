package com.example.repository;

import com.example.entity.Invitation;
import com.example.entity.Party;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findByUser(User user);
    List<Invitation> findByParty(Party party);
    List<Invitation> findByUserAndStatus(User user, Invitation.InvitationStatus status);
}
