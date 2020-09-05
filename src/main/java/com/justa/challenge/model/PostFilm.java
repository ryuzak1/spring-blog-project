package com.justa.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostFilm {

    @Id
    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
}
