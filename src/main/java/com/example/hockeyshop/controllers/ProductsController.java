package com.example.hockeyshop.controllers;


import com.example.hockeyshop.dtos.ProductResponseDto;
import com.example.hockeyshop.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@CrossOrigin
public class ProductsController {

    private ProductsService productsService;

    @GetMapping("get-all")
    public List<ProductResponseDto> getAll(){
        return productsService.getAll();
    }

    @GetMapping("get-by-category-id/{categoryId}")
    public List<ProductResponseDto> getByCategoryId(@PathVariable int categoryId){
        return productsService.getByCategoryId(categoryId);
    }
}
