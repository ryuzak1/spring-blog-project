package com.justa.challenge.controller.form;

import com.justa.challenge.model.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryForm {

    @NotEmpty
    @NotNull
    private String name;
    public Category convert(CategoryForm categoryForm) {

        return new Category(categoryForm.getName());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
