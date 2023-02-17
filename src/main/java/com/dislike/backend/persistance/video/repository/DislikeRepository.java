package com.dislike.backend.persistance.video.repository;

import com.dislike.backend.persistance.video.model.DislikePersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DislikeRepository extends JpaRepository<DislikePersistence, String> {

    Optional<DislikePersistence> findFirstByVideoPersistence_IdAndUserPersistence_Username(String videoId, String username);

    default Optional<DislikePersistence> findByVideoIdAndUsername(String videoId, String username) {
        return findFirstByVideoPersistence_IdAndUserPersistence_Username(videoId, username);
    }
}
