package com.justa.challenge.controller.dto;

import com.justa.challenge.model.Comment;

import java.time.LocalDateTime;

public class CommentDto {

    private Long id;
    private String menssage;
    private LocalDateTime createDate;
    private String nameAuthor;


    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.menssage = comment.getComment();
        this.createDate = comment.getCreationDate();
        this.nameAuthor = comment.getAutor().getName();
    }


    public Long getId() {
        return id;
    }

    public String getMenssage() {
        return menssage;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }
}
