package com.example.hockeyshop.controllers;


import com.example.hockeyshop.api.SmsApiWorker;
import com.example.hockeyshop.auth.AuthOrRegisterService;
import com.example.hockeyshop.bot.BotInitializer;
import com.example.hockeyshop.dtos.AuthOrRegisterResponseDto;
import com.example.hockeyshop.dtos.AuthRequestDto;
import com.example.hockeyshop.dtos.CheckPhoneNumberRequestDto;
import com.example.hockeyshop.utils.RandomSmsCodeUtil;
import com.example.hockeyshop.utils.RedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthOrRegisterService authOrRegisterService;
    private RandomSmsCodeUtil randomSmsCodeUtil;
    private RedisUtil redisUtil;
    private SmsApiWorker smsApiWorker;
    private BotInitializer botInitializer;

//    @PostMapping("/register")
//    public AuthOrRegisterResponseDto register(@RequestBody RegisterRequestDto registerRequestDto) {
//        return authOrRegisterService.register(registerRequestDto);
//    }

    @PostMapping("/check-phone-number")
    public ResponseEntity checkPhoneNumber(@RequestBody CheckPhoneNumberRequestDto checkPhoneNumberRequestDto) throws TelegramApiException {
        String phoneNumber = checkPhoneNumberRequestDto.getPhoneNumber();

        boolean userExist = authOrRegisterService.checkPhoneNumber(phoneNumber);

        if (!userExist) {
            return new ResponseEntity(HttpStatusCode.valueOf(403));
        }

        String smsCode = randomSmsCodeUtil.getRandomCode();
        redisUtil.set(phoneNumber, smsCode);
        //smsApiWorker.send(phoneNumber, smsCode);
        botInitializer.sendCodeToChat(phoneNumber,smsCode);

        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/authenticate")
    public AuthOrRegisterResponseDto authenticate(@RequestBody AuthRequestDto authRequestDto) {
        return authOrRegisterService.authenticate(authRequestDto);
    }
}
