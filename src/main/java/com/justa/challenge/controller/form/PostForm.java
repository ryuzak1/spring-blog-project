package com.justa.challenge.controller.form;

import com.justa.challenge.model.Category;
import com.justa.challenge.model.Post;
import com.justa.challenge.repository.CategoryRepository;
import com.justa.challenge.repository.PostRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostForm {


    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    @Length(min = 5, max = 240)
    private String content;
    @NotEmpty
    @NotNull
    private String categoryName;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Post convert(CategoryRepository categoryRepository) {
        Category category=categoryRepository.findByName(this.categoryName);

        return new Post(title,content,category);
    }


}
