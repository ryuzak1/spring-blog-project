package com.justa.challenge.controller;


import com.justa.challenge.controller.dto.DetailPostDto;
import com.justa.challenge.controller.dto.PostDto;
import com.justa.challenge.controller.form.PostForm;
import com.justa.challenge.controller.form.PostUpdateForm;
import com.justa.challenge.model.Post;

import com.justa.challenge.repository.CategoryRepository;
import com.justa.challenge.repository.PostRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public Page<PostDto> postList(@RequestParam(required = false) String nome,
                                  @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                          Pageable pageable) {

        if (nome == null) {
            Page<Post> posts = postRepository.findAll(pageable);
            return PostDto.convert(posts);

        } else {
             Page<Post> posts = postRepository.findByTitle(nome,pageable);
            return PostDto.convert(posts);
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<PostDto> savePost(@RequestBody @Valid PostForm postForm, UriComponentsBuilder uriComponentsBuilder) {
        Post post = postForm.convert(categoryRepository);
        postRepository.save(post);
        URI uri = uriComponentsBuilder.path("/post/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostDto(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailPostDto> detailPost(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(new DetailPostDto(post.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody @Valid PostUpdateForm postUpdateForm) {

        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postUpdateForm.updatePost(id, postRepository);
            return ResponseEntity.ok(new PostDto(post));
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
