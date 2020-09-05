package com.justa.challenge.controller.dto;

import com.justa.challenge.model.Post;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class PostDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;


    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdDate = post.getCreateDate();
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public static Page<PostDto> convert(Page<Post> posts){
        return posts.map(PostDto::new);
    }

}
