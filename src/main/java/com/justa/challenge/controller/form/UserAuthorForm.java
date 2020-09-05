package com.justa.challenge.controller.form;

import com.justa.challenge.model.UserAuthor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserAuthorForm {

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String password;


    public UserAuthor convert(UserAuthorForm userAuthorForm) {

        userAuthorForm.setPassword(new BCryptPasswordEncoder().encode(userAuthorForm.getPassword()));

        return new UserAuthor(userAuthorForm.getName(),userAuthorForm.getEmail(),userAuthorForm.getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
