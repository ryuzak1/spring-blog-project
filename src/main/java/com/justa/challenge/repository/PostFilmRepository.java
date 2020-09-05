package com.justa.challenge.repository;

import com.justa.challenge.model.PostFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFilmRepository extends JpaRepository<PostFilm, String> {
    Page<PostFilm> findByTitle(String nome, Pageable pageable);
}
