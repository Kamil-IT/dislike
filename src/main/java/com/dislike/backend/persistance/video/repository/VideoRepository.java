package com.dislike.backend.persistance.video.repository;

import com.dislike.backend.persistance.video.model.VideoPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<VideoPersistence, String> {
}
