package com.justa.challenge.controller;

import com.justa.challenge.controller.dto.PostDto;
import com.justa.challenge.controller.dto.PostFilmDto;
import com.justa.challenge.controller.form.UpdatePostFilmForm;
import com.justa.challenge.model.Post;
import com.justa.challenge.model.PostFilm;
import com.justa.challenge.repository.PostFilmRepository;
import com.justa.challenge.service.PostFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/film")
public class PostFilmController {


    @Autowired
    private PostFilmService postFilmService;

    @Autowired
    private PostFilmRepository postFilmRepository;


    @GetMapping
    public Page<PostFilmDto> listPostFilmDataBase(@RequestParam(required = false) String nome,
                                      @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                          Pageable pageable) {

        if (nome == null) {
            Page<PostFilm> posts = postFilmRepository.findAll(pageable);
            return PostFilmDto.convert(posts);

        } else {
            Page<PostFilm> posts = postFilmRepository.findByTitle(nome,pageable);
            return PostFilmDto.convert(posts);
        }

    }
    @GetMapping("{id}")
    public ResponseEntity<PostFilmDto> getPostFilm(@PathVariable("id") String id){
        Optional<PostFilm> postFilm = postFilmRepository.findById(id);
        if (postFilm.isPresent()){
            return ResponseEntity.ok(new PostFilmDto(postFilm.get()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delePostFilm(@PathVariable String id){
        Optional<PostFilm> optionalPostFilm = postFilmRepository.findById(id);
        if(optionalPostFilm.isPresent()){
            postFilmRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PostFilmDto> updatePostFilm(@PathVariable String id,
                                                      @RequestBody @Valid UpdatePostFilmForm updatePostFilmForm){
        Optional<PostFilm> optionalPostFilm = postFilmRepository.findById(id);

        if (optionalPostFilm.isPresent()){
            PostFilm postFilm = updatePostFilmForm.updateFilm(id,postFilmRepository);
            return ResponseEntity.ok(new PostFilmDto(postFilm));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/create/{id}")
    public ResponseEntity<?> createPostFilm(@PathVariable("id") String id){

        try {
            PostFilm p =  postFilmService.showOneFilm(id);
            postFilmRepository.save(p);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/all")
    @Cacheable(value = "list-film")
    public ResponseEntity<PostFilm[]> listAllFilmFromAPI(){

        System.out.println("carregegand....");
        return ResponseEntity.ok(postFilmService.showAllFilm());
    }



}
