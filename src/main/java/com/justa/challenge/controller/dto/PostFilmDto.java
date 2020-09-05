package com.justa.challenge.controller.dto;

import com.justa.challenge.model.PostFilm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
public class PostFilmDto {


    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;


    public PostFilmDto(PostFilm postFilm){
        this.id = postFilm.getId();
        this.title = postFilm.getTitle();
        this.description = postFilm.getDescription();
        this.director = postFilm.getDirector();
        this.producer = postFilm.getProducer();
    }

    public static Page<PostFilmDto> convert(Page<PostFilm> postFilms) {
        return postFilms.map(PostFilmDto::new);
    }
}
