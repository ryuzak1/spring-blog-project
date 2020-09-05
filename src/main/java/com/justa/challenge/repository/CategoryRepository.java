package com.justa.challenge.repository;

import com.justa.challenge.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Page<Category> findByName(String name, Pageable pageable);

    Category findByName(String name);
}
