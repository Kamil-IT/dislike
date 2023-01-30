package com.dislike.backend.persistance.video.repository;

import com.dislike.backend.persistance.video.model.VideoDislikePersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRatingRepository extends JpaRepository<VideoDislikePersistence, String> {

    Optional<VideoDislikePersistence> findFirstByVideoPersistence_IdAndUserPersistence_Username(String videoId, String username);

    default Optional<VideoDislikePersistence> findByVideoIdAndUsername(String videoId, String username) {
        return findFirstByVideoPersistence_IdAndUserPersistence_Username(videoId, username);
    }
}
