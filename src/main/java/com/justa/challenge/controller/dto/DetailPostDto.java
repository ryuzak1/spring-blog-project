package com.justa.challenge.controller.dto;

import com.justa.challenge.model.Comment;
import com.justa.challenge.model.Post;
import com.justa.challenge.model.StatusPost;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailPostDto {


    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private String userAthor;
    private StatusPost statusPost;
    private List<CommentDto>commentList;


    public DetailPostDto(Post post){
        this.id= post.getId();
        this.title=post.getTitle();
        this.content = post.getContent();
        this.createDate=post.getCreateDate();
        this.userAthor=post.getUser().getName();
        this.statusPost = post.getStatusPost();
        this.commentList = new ArrayList<>();
        this.commentList.addAll(post.getComments().stream().map(CommentDto::new).collect(Collectors.toList()));
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getUserAthor() {
        return userAthor;
    }

    public StatusPost getStatusPost() {
        return statusPost;
    }

    public List<CommentDto> getCommentList() {
        return commentList;
    }
}
