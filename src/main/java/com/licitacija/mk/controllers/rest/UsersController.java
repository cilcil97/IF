package com.licitacija.mk.controllers.rest;

import com.licitacija.mk.config.CurrentUser;
import com.licitacija.mk.config.UserPrincipal;
import com.licitacija.mk.models.User;
import com.licitacija.mk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
