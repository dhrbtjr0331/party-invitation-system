package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Invitation;
import com.example.service.InvitationService;

@RestController
@RequestMapping("/api/invitations")
@CrossOrigin(origins = "http://localhost:3000")
public class InvitationController {

    private final InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping
    public ResponseEntity<Invitation> createInvitation(@RequestBody Invitation invitation) {
        Invitation createdInvitation = invitationService.createInvitation(invitation);
        return ResponseEntity.ok(createdInvitation);
    }

    @GetMapping
    public ResponseEntity<List<Invitation>> getAllInvitations() {
        List<Invitation> invitations = invitationService.getAllInvitations();
        return ResponseEntity.ok(invitations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invitation> getInvitationById(@PathVariable Long id) {
        return invitationService.getInvitationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Invitation> updateInvitationStatus(@PathVariable Long id, @RequestBody Invitation.InvitationStatus status) {
        Invitation updatedInvitation = invitationService.updateInvitationStatus(id, status);
        return ResponseEntity.ok(updatedInvitation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable Long id) {
        invitationService.deleteInvitation(id);
        return ResponseEntity.noContent().build();
    }
}

