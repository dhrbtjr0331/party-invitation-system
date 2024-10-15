/**
 * Let's break down this code:

@Entity: This annotation tells Spring that this class is a JPA entity, which means it will be mapped to a database table.

@Table(name = "users"): This specifies the name of the database table for this entity. We use "users" instead of "user" because "user" is a reserved keyword in some databases.

@Id: This annotation marks the id field as the primary key of the entity.

@GeneratedValue(strategy = GenerationType.IDENTITY): This tells the database to automatically generate and increment the id for new entries.

@Column: This annotation is used to specify column mappings. nullable = false means the column can't contain null values, and unique = true means each value in this column must be unique.

@OneToMany: This establishes a one-to-many relationship. A user can host many parties and have many invitations.

mappedBy: This is used in the inverse side of the relationship to indicate that the relationship is already mapped by the other entity.

 */

package com.example.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "host")
    @JsonManagedReference // Manages the reference and prevents infinite recursion
    private Set<Party> hostedParties;

    @OneToMany(mappedBy = "user")
    private Set<Invitation> invitations;
 
    // Constructors
    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Party> getHostedParties() {
        return hostedParties;
    }

    public void setHostedParties(Set<Party> hostedParties) {
        this.hostedParties = hostedParties;
    }

    public Set<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<Invitation> invitations) {
        this.invitations = invitations;
    }
}
 
