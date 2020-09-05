package com.justa.challenge.controller.dto;

import com.justa.challenge.model.UserAuthor;
import org.springframework.data.domain.Page;

public class UserAuthorDto {

    private Long id;
    private String name;


    public UserAuthorDto(UserAuthor userAuthor){
        this.id=userAuthor.getId();
        this.name = userAuthor.getName();

    }

    public static Page<UserAuthorDto> convert(Page<UserAuthor> userAuthors) {

        return userAuthors.map(UserAuthorDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
