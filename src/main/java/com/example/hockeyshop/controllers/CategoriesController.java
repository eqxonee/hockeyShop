package com.example.hockeyshop.controllers;

import com.example.hockeyshop.dtos.CategoryResponseDto;
import com.example.hockeyshop.services.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
@AllArgsConstructor
//@CrossOrigin
public class CategoriesController {
    private CategoriesService categoriesService;

    @GetMapping("get-all")
    public List<CategoryResponseDto> getAll() {
        return categoriesService.getAll();
    }
}
