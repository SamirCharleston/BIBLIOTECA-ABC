package com.sismanut.sismanut.coreClasses.userAutentication;

import com.sismanut.sismanut.config.customExceptions.UserNotFoundException;
import com.sismanut.sismanut.config.messageHandling.errorMessages.ExceptionMessages;
import com.sismanut.sismanut.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserDefaultRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = repository.findByLogin(username);

        if(userDetails == null){
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }

        return userDetails;
    }
}
