package com.justa.challenge.controller.form;

import com.justa.challenge.model.Category;
import com.justa.challenge.repository.CategoryRepository;

public class CategoryUpdateForm {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category updateCategory(Long id, CategoryRepository categoryRepository){
        Category category = categoryRepository.getOne(id);
        category.setName(this.name);
        return category;
    }
}
