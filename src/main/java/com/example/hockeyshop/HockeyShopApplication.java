package com.example.hockeyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HockeyShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(HockeyShopApplication.class, args);

        int number = 5;
        int number2 = 10;
        String exception = "Ded chmo";

        int sum = number + number2;

        System.out.println(sum);
        System.out.println(exception);
    }

}
