package com.dislike.backend.persistance.user.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    private String login;
    private String password;
}
