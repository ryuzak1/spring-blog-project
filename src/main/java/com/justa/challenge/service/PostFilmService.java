package com.justa.challenge.service;


import com.justa.challenge.model.PostFilm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostFilmService {

    public PostFilm[] showAllFilm(){
        RestTemplate template = new RestTemplate();
        PostFilm[] postFilms = template.getForObject("https://ghibliapi.herokuapp.com/films/",PostFilm[].class);
        System.out.println(postFilms);
        return postFilms;
    }
    public PostFilm showOneFilm(String id){
        RestTemplate template = new RestTemplate();
        PostFilm entity = template.getForObject("https://ghibliapi.herokuapp.com/films/"+id,PostFilm.class);
        return entity;
    }
}
