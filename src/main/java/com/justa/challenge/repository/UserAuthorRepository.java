package com.justa.challenge.repository;


import com.justa.challenge.model.UserAuthor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthorRepository extends JpaRepository<UserAuthor, Long> {
    Page<UserAuthor> findByName(String name, Pageable pageable);
    Optional<UserAuthor> findByEmail(String name);
}
