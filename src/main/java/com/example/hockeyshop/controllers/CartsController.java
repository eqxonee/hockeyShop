package com.example.hockeyshop.controllers;


import com.example.hockeyshop.dtos.InsertToCartDto;
import com.example.hockeyshop.dtos.ShowUserCartDto;
import com.example.hockeyshop.services.CartsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
@AllArgsConstructor
public class CartsController {
    private CartsService cartsService;

    @PostMapping("add-new")
    public void addNew(@RequestBody InsertToCartDto insertToCartDto) {
        cartsService.addNew(insertToCartDto);
    }

    @GetMapping("get-by-userid/{userId}")
    public List<ShowUserCartDto> getCartByUserId(@PathVariable int userId) {
        return cartsService.getCartByUserId(userId);
    }
}
