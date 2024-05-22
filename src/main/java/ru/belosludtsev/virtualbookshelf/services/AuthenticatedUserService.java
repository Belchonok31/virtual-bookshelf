package ru.belosludtsev.virtualbookshelf.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.MeUserDetails;
import ru.belosludtsev.virtualbookshelf.entities.User;

@Service
public class AuthenticatedUserService {
    public User getAuthenticatedUser(){
        MeUserDetails userDetails = (MeUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDetails.getUser();
    }
}
