package com.justa.challenge.config.security;

import com.justa.challenge.model.UserAuthor;
import com.justa.challenge.repository.UserAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private UserAuthorRepository userAuthorRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserAuthor> userAuthor= userAuthorRepository.findByEmail(s);

        if (userAuthor.isPresent()){
            return userAuthor.get();
        }
        throw new UsernameNotFoundException("Dados Invalidos");
    }
}
