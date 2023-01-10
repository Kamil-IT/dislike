package com.dislike.backend.persistance.video.repository;

import com.dislike.backend.persistance.video.model.VideoDislikePersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRatingRepository extends JpaRepository<VideoDislikePersistence, String> {
}
