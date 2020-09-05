package com.justa.challenge.controller;


import com.justa.challenge.controller.dto.UserAuthorDto;
import com.justa.challenge.controller.form.UserAuthorForm;
import com.justa.challenge.controller.form.UserAuthorUpdateForm;
import com.justa.challenge.model.UserAuthor;
import com.justa.challenge.repository.UserAuthorRepository;
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
@RequestMapping("/user")
public class UserAuthorController {


    @Autowired
    private UserAuthorRepository userAuthorRepository;

    @GetMapping
    public Page<UserAuthorDto> userAuthorList(@RequestParam(required = false) String name,
                                              @PageableDefault(sort = "id",
                                                      direction = Sort.Direction.ASC, page = 0, size = 10)
                                                      Pageable pageable) {

        if (name == null) {
            Page<UserAuthor> userAuthors = userAuthorRepository.findAll(pageable);
            return UserAuthorDto.convert(userAuthors);
        } else {
            Page<UserAuthor> userAuthors = userAuthorRepository.findByName(name, pageable);
            return UserAuthorDto.convert(userAuthors);
        }
    }

    @PostMapping
    public ResponseEntity<UserAuthorDto> createUserAuthor(@RequestBody @Valid UserAuthorForm userAuthorForm,
                                                          UriComponentsBuilder uriComponentsBuilder) {

        UserAuthor userAuthor = userAuthorForm.convert(userAuthorForm);
        System.out.println(userAuthor.getPassword());
        userAuthorRepository.save(userAuthor);
        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(userAuthor.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserAuthorDto(userAuthor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAuthorDto> getUserAuthor(@PathVariable Long id) {
        Optional<UserAuthor> userAuthor = userAuthorRepository.findById(id);

        if (userAuthor.isPresent()) {
            return ResponseEntity.ok(new UserAuthorDto(userAuthor.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserAuthorDto> updateUserAuthor(@PathVariable Long id,
                                                          @RequestBody @Valid UserAuthorUpdateForm userAuthorForm) {
        Optional<UserAuthor> getUserAuthor = userAuthorRepository.findById(id);
        if (getUserAuthor.isPresent()) {
            UserAuthor userAuthor = userAuthorForm.updateUserAuthor(id, userAuthorRepository);
            return ResponseEntity.ok(new UserAuthorDto(userAuthor));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletUserAuthor(@PathVariable Long id){
        Optional<UserAuthor> userAuthor = userAuthorRepository.findById(id);

        if (userAuthor.isPresent()){
            userAuthorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
