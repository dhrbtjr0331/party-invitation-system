package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.example.entity")
@SpringBootApplication
public class PartyInvitationSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(PartyInvitationSystemApplication.class, args);
    }
}
