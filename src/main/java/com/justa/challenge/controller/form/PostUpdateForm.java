package com.justa.challenge.controller.form;

import com.justa.challenge.model.Post;
import com.justa.challenge.repository.PostRepository;

public class PostUpdateForm {

    private String title;
    private String content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post updatePost(Long id, PostRepository postRepository) {
        Post post = postRepository.getOne(id);

        if (this.getTitle() == null) {
            post.setTitle(post.getTitle());
        } else {
            post.setTitle(this.title);
        }
        if (this.getContent() == null) {
            post.setContent(post.getContent());
        } else {
            post.setContent(this.content);
        }
        return post;
    }
}
