package com.justa.challenge.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    private Post post;
    private LocalDateTime creationDate = LocalDateTime.now();
    @ManyToOne
    private UserAuthor autor;
    private Boolean situation = null;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) &&
                this.comment.equals(comment.comment) &&
                post.equals(comment.post) &&
                creationDate.equals(comment.creationDate) &&
                autor.equals(comment.autor) &&
                situation.equals(comment.situation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, post, creationDate, autor, situation);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UserAuthor getAutor() {
        return autor;
    }

    public void setAutor(UserAuthor autor) {
        this.autor = autor;
    }

    public Boolean getSituation() {
        return situation;
    }

    public void setSituation(Boolean situation) {
        this.situation = situation;
    }
}
