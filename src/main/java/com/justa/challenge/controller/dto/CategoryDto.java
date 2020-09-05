package com.justa.challenge.controller.dto;

import com.justa.challenge.model.Category;
import org.springframework.data.domain.Page;

public class CategoryDto {

    private Long id;
    private String name;


    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Page<CategoryDto> convert(Page<Category> categories){
        return categories.map(CategoryDto::new);

    }
}
