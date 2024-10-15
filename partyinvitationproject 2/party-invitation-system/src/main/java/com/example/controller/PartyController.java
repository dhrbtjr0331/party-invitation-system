package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.entity.Party;
import com.example.entity.User;
import com.example.service.PartyService;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/parties")
@CrossOrigin(origins = "http://localhost:3000")
public class PartyController {

    private final PartyService partyService;
    private final UserService userService;

    @Autowired
    public PartyController(PartyService partyService, UserService userService) {
        this.partyService = partyService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createParty(@RequestBody Party party) {
        // Fetch host by username
        Optional<User> hostOptional = userService.getUserByUsername(party.getHost().getUsername());

        if (hostOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Host user not found"); // Return a meaningful message
        }

        User host = hostOptional.get();
        party.setHost(host); // Set the found user as host

        try {
            Party createdParty = partyService.createParty(party);
            return ResponseEntity.ok("Party created successfully with ID: " + createdParty.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating party: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Party>> getAllParties() {
        List<Party> parties = partyService.getAllParties();
        return ResponseEntity.ok(parties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Party> getPartyById(@PathVariable Long id) {
        return partyService.getPartyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());  // Return 404 if party not found
    }

    @GetMapping("/public")
    public ResponseEntity<List<Party>> getPublicParties() {
        List<Party> publicParties = partyService.getPublicParties();
        return ResponseEntity.ok(publicParties);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParty(@PathVariable Long id, @RequestBody Party party) {
        Optional<Party> existingParty = partyService.getPartyById(id);

        if (existingParty.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Party not found");
        }

        try {
            party.setId(id);
            Party updatedParty = partyService.updateParty(party);
            return ResponseEntity.ok(updatedParty);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating party: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParty(@PathVariable Long id) {
        Optional<Party> existingParty = partyService.getPartyById(id);

        if (existingParty.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Party not found");
        }

        try {
            partyService.deleteParty(id);
            return ResponseEntity.noContent().build(); // No content for successful delete
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting party: " + e.getMessage());
        }
    }
}

