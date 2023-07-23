package com.example.hockeyshop.controllers;


import com.example.hockeyshop.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private UsersService usersService;

    @GetMapping("/get-id-by-token")
    public int getIdByToken(Principal principal){
        return usersService.findByPhoneNumber(principal.getName()).get().getId();
    }
}
