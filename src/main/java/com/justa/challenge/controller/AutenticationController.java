package com.justa.challenge.controller;


import com.justa.challenge.config.security.TokenService;
import com.justa.challenge.controller.dto.TokenDto;
import com.justa.challenge.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<TokenDto> autentication(@RequestBody @Valid LoginForm formLogin){

        UsernamePasswordAuthenticationToken dataLogin = formLogin.convert();


        try {

            Authentication authentication = authenticationManager.authenticate(dataLogin);
            String token = tokenService.generationToken(authentication);

            return ResponseEntity.ok(new TokenDto(token,"Bearer"));

        }catch (AuthenticationException e){
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();

        }

    }


}
