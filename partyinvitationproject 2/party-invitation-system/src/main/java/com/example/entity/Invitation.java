/*
 * @Enumerated(EnumType.STRING): This annotation is used with enum types. It tells JPA to store the enum as a string in the database, rather than as an ordinal number.

We've defined an enum for the invitation status, which provides a fixed set of possible values.
 */

package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User is required")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Party is required")
    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @NotNull(message = "Sent date is required")
    @PastOrPresent(message = "Sent date must be in the past or present")
    @Column(nullable = false)
    private LocalDateTime sentAt;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    // Constructors
    public Invitation() {}

    public Invitation(User user, Party party, LocalDateTime sentAt, InvitationStatus status) {
        this.user = user;
        this.party = party;
        this.sentAt = sentAt;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    // Enum for Invitation Status
    public enum InvitationStatus {
        PENDING, ACCEPTED, DECLINED
    }
}
 