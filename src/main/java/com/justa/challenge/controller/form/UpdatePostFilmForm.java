package com.justa.challenge.controller.form;

import com.justa.challenge.model.PostFilm;
import com.justa.challenge.repository.PostFilmRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdatePostFilmForm {


    @NotNull
    private String title;
    @NotNull
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostFilm updateFilm(String id, PostFilmRepository postFilmRepository) {
        PostFilm postFilm = postFilmRepository.getOne(id);
        if (this.getTitle() == null) {
            postFilm.setTitle(postFilm.getTitle());
        } else {
            postFilm.setTitle(this.title);
        }
        if (this.getDescription() == null) {
            postFilm.setDescription(postFilm.getDescription());
        } else {
            postFilm.setDescription(this.description);
        }


        return postFilm;
    }
}
