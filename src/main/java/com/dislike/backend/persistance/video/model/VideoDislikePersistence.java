package com.dislike.backend.persistance.video.model;

import com.dislike.backend.persistance.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class VideoDislikePersistence {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
