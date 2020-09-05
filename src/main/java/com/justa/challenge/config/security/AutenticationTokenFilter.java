package com.justa.challenge.config.security;

import com.justa.challenge.model.UserAuthor;
import com.justa.challenge.repository.UserAuthorRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserAuthorRepository userAuthorRepository;

    public AutenticationTokenFilter(TokenService tokenService, UserAuthorRepository userAuthorRepository) {
        this.tokenService = tokenService;
        this.userAuthorRepository = userAuthorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoveryToken(request);

        boolean validation = tokenService.isValid(token);
        if (validation){
            autenticationUser(token);
        }

        filterChain.doFilter(request,response);
    }
    private void autenticationUser(String token) {

        Long idUser = tokenService.getIdUser(token);
        UserAuthor user = userAuthorRepository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    private String recoveryToken(HttpServletRequest request) {
        String token =  request.getHeader("Authorization");

        if(token==null||token.isEmpty()||!token.startsWith("Bearer ")){
            return null;
        }
        else{
            return token.substring(7,token.length());

        }


    }
}
