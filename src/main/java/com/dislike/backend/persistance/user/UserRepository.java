package com.dislike.backend.persistance.user;

import com.dislike.backend.persistance.user.model.UserPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPersistence, String> {

    Optional<UserPersistence> findFirstByUsername(String username);
}
