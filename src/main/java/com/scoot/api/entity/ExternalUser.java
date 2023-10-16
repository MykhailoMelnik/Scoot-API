package com.scoot.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ExternalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(name = "api_key")
    private String apiKey;
}

