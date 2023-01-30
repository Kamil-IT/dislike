package com.dislike.backend.persistance.video.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class VideoPersistence {

    @Id
    private String id;
    private String name;

    @OneToMany
    private Collection<VideoDislikePersistence> videoDislikePersistences = new ArrayList<>();
}
