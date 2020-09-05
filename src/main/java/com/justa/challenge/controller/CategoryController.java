package com.justa.challenge.controller;


import com.justa.challenge.controller.dto.CategoryDto;
import com.justa.challenge.controller.form.CategoryForm;
import com.justa.challenge.controller.form.CategoryUpdateForm;
import com.justa.challenge.model.Category;
import com.justa.challenge.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public Page<CategoryDto> listCategory(@RequestParam(required = false) String name,
                                          @PageableDefault(
                                                  sort = "id",
                                                  direction = Sort.Direction.ASC,
                                                  page = 0, size = 5)
                                                  Pageable pageable) {

        if (name == null) {
            Page<Category> categories = categoryRepository.findAll(pageable);
            return CategoryDto.convert(categories);
        } else {
            Page<Category> categories = categoryRepository.findByName(name, pageable);
            return CategoryDto.convert(categories);
        }


    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryForm categoryForm, UriComponentsBuilder uriComponentsBuilder) {
        Category category = categoryForm.convert(categoryForm);
        System.out.println(category.getId());
        categoryRepository.save(category);
        URI uri = uriComponentsBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoryDto(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {

        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(new CategoryDto(category.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryUpdateForm categoryUpdateForm) {
        Optional<Category> optionalCategorycategory = categoryRepository.findById(id);

        if (optionalCategorycategory.isPresent()) {
            Category category = categoryUpdateForm.updateCategory(id, categoryRepository);
            return ResponseEntity.ok(new CategoryDto(category));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleCateogry(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }

}
