package com.justa.challenge.repository;

import com.justa.challenge.model.Category;
import com.justa.challenge.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long>{

  Page<Post> findByTitle(String name, Pageable pageable);

}
