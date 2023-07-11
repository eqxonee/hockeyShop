package com.example.hockeyshop.services;


import com.example.hockeyshop.dtos.CategoryResponseDto;
import com.example.hockeyshop.models.Category;
import com.example.hockeyshop.repositories.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriesService {
    private ModelMapper modelMapper;
    private CategoriesRepository categoriesRepository;

    public List<CategoryResponseDto> getAll() {
        List<Category> categories = categoriesRepository.findAll();

        return categories.stream()
                .map(category -> modelMapper.map(category,CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

}
