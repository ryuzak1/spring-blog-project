package com.justa.challenge.controller.form;

import com.justa.challenge.model.UserAuthor;
import com.justa.challenge.repository.UserAuthorRepository;

public class UserAuthorUpdateForm {

    private String name;
    private String password;


    public UserAuthor updateUserAuthor(Long id, UserAuthorRepository userAuthorRepository){
        UserAuthor userAuthor = userAuthorRepository.getOne(id);

        if (this.getName()==null){
            userAuthor.setName(userAuthor.getName());
        }else {
            userAuthor.setName(this.name);
        }if(this.getPassword()==null){
            userAuthor.setPassword(userAuthor.getPassword());
        }else {
            userAuthor.setPassword(this.password);
        }
        return userAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
