/*
LocalDateTime: This is a Java class used to represent a date-time without a time zone.

@ManyToOne: This annotation establishes a many-to-one relationship. Many parties can be hosted by one user.

@JoinColumn: This specifies the foreign key column for the relationship.
 */
package com.example.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "parties")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Party name is required")
    @Size(max = 100, message = "Party name must not exceed 100 characters")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Date and time is required")
    @Future(message = "Party date must be in the future")
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    @JsonBackReference // Prevents infinite recursion during serialization
    private User host;

    @OneToMany(mappedBy = "party")
    private Set<Invitation> invitations;

    // Constructors
    public Party() {}

    public Party(String name, LocalDateTime dateTime, String location, boolean isPublic, User host) {
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.isPublic = isPublic;
        this.host = host;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public Set<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<Invitation> invitations) {
        this.invitations = invitations;
    }
}

