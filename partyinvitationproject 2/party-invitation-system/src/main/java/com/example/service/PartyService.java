package com.example.service;

import com.example.entity.Party;
import com.example.entity.User;
import com.example.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @Transactional
    public Party createParty(Party party) {
        return partyRepository.save(party);
    }

    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    public Optional<Party> getPartyById(Long id) {
        return partyRepository.findById(id);
    }

    public List<Party> getPartiesByHost(User host) {
        return partyRepository.findByHost(host);
    }

    public List<Party> getPublicParties() {
        return partyRepository.findByIsPublic(true);
    }

    public Party updateParty(Party party) {
        if (!partyRepository.existsById(party.getId())) {
            throw new IllegalArgumentException("Party not found");
        }
        return partyRepository.save(party);
    }

    public void deleteParty(Long id) {
        partyRepository.deleteById(id);
    }
}
