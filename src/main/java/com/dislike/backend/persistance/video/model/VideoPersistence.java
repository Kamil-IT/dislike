package com.dislike.backend.persistance.video.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class VideoPersistence {

    @Id
    private Long id;
    private String name;
}
