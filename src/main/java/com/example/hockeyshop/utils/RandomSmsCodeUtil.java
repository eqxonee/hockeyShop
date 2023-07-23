package com.example.hockeyshop.utils;

import org.springframework.stereotype.Service;

@Service
public class RandomSmsCodeUtil {
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getRandomCode() {
        return Integer.toString(getRandomNumber(1000, 10000));
    }
}
