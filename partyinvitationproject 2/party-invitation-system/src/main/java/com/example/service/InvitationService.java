package com.example.service;

import com.example.entity.Invitation;
import com.example.entity.Party;
import com.example.entity.User;
import com.example.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Transactional
    public Invitation createInvitation(Invitation invitation) {
        invitation.setSentAt(LocalDateTime.now());
        invitation.setStatus(Invitation.InvitationStatus.PENDING);
        return invitationRepository.save(invitation);
    }

    public List<Invitation> getAllInvitations() {
        return invitationRepository.findAll();
    }

    public Optional<Invitation> getInvitationById(Long id) {
        return invitationRepository.findById(id);
    }

    public List<Invitation> getInvitationsByUser(User user) {
        return invitationRepository.findByUser(user);
    }

    public List<Invitation> getInvitationsByParty(Party party) {
        return invitationRepository.findByParty(party);
    }

    public Invitation updateInvitationStatus(Long id, Invitation.InvitationStatus status) {
        Invitation invitation = invitationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invitation not found"));
        invitation.setStatus(status);
        return invitationRepository.save(invitation);
    }

    public void deleteInvitation(Long id) {
        invitationRepository.deleteById(id);
    }
}
