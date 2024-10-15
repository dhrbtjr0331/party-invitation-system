/*
@Repository is a Spring annotation that indicates this interface is a repository.
We extend JpaRepository<User, Long>, where User is the entity type and Long is the type of the entity's ID.
JpaRepository provides basic CRUD operations out of the box.
We've added some custom query methods like findByUsername and findByEmail. Spring Data JPA will automatically implement these based on the method names.
 */
package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
