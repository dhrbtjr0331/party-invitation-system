package com.example.service;

import com.example.entity.User;
import com.example.entity.Party;
import com.example.repository.PartyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartyServiceTest {

    @InjectMocks
    private PartyService partyService;

    @Mock
    private PartyRepository partyRepository;

    private User host;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        host = new User(); // Assuming a User object is required for party creation
        // Initialize the host object if needed
    }

    @Test
    public void testCreateParty_success() {
        // Arrange
        Party party = new Party("Halloween Bash", LocalDateTime.now().plusDays(5), "NYC", true, host);
        when(partyRepository.save(any(Party.class))).thenReturn(party);

        // Act
        Party createdParty = partyService.createParty(party);

        // Assert
        assertEquals("Halloween Bash", createdParty.getName());
    }
}

