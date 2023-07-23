package com.example.hockeyshop.auth;

import com.example.hockeyshop.dtos.AuthOrRegisterResponseDto;
import com.example.hockeyshop.dtos.AuthRequestDto;
import com.example.hockeyshop.dtos.RegisterRequestDto;
import com.example.hockeyshop.models.User;
import com.example.hockeyshop.services.UsersService;
import com.example.hockeyshop.utils.RedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthOrRegisterService {
    private UsersService usersService;
    private JwtService jwtService;
    private RedisUtil redisUtil;

    public AuthOrRegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        User user = User.builder()
                .name(registerRequestDto.getName())
                .phoneNumber(registerRequestDto.getPhoneNumber())
                .build();

        usersService.addNew(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthOrRegisterResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthOrRegisterResponseDto authenticate(AuthRequestDto authRequestDto) {

        String code = redisUtil.get(authRequestDto.getPhoneNumber());

        if (code == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!authRequestDto.getSmsCode().equals(code)) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = usersService.findByPhoneNumber(authRequestDto.getPhoneNumber()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);

        return AuthOrRegisterResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        return usersService.findByPhoneNumber(phoneNumber).isPresent();
    }
}
