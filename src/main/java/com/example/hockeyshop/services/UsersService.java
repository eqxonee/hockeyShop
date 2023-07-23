package com.example.hockeyshop.services;

import com.example.hockeyshop.models.User;
import com.example.hockeyshop.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    private UsersRepository usersRepository;

    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return usersRepository
                .findByPhoneNumber(phoneNumber);
    }

    public void addNew(User user){
        usersRepository.save(user);
    }
}
