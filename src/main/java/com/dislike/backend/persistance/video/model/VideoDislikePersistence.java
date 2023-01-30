package com.dislike.backend.persistance.video.model;

import com.dislike.backend.persistance.user.model.UserPersistence;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDislikePersistence {

    @Id
    private String id;

    @ManyToOne
    private VideoPersistence videoPersistence;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserPersistence userPersistence;
}
