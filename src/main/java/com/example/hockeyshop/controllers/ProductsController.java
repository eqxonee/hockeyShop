package com.example.hockeyshop.controllers;


import com.example.hockeyshop.dtos.ProductResponseDto;
import com.example.hockeyshop.services.FilService;
import com.example.hockeyshop.services.ProductsService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
//@CrossOrigin
public class ProductsController {

    private ProductsService productsService;
    private FilService filService;

    @GetMapping("get-all")
    public List<ProductResponseDto> getAll(){
        return productsService.getAll();
    }

    @GetMapping("get-by-category-id/{categoryId}")
    public List<ProductResponseDto> getByCategoryId(@PathVariable int categoryId){
        return productsService.getByCategoryId(categoryId);
    }

    @GetMapping("get-all-filter")
    public List<ProductResponseDto> getAllFilter(@RequestParam @NotNull String name,
                                           @RequestParam @NotNull String grip,
                                           @RequestParam @NotNull String bend){
        return filService.filterProducts(name,grip,bend);
    }


}
