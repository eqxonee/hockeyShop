package com.example.hockeyshop.services;

import com.example.hockeyshop.dtos.ProductResponseDto;
import com.example.hockeyshop.models.Product;
import com.example.hockeyshop.repositories.FilterProduct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilService {

    private ModelMapper modelMapper;
    private FilterProduct filterProduct;


    public List<ProductResponseDto> filterProducts(String name, String grip, String bend) {
        List<Product> products = filterProduct.findAllByFilter(name, grip, bend);

        return products.stream().map(product -> modelMapper
                        .map(product, ProductResponseDto.class))
                .collect(Collectors.toList());

    }

}
