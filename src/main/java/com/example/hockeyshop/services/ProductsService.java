package com.example.hockeyshop.services;

import com.example.hockeyshop.dtos.ProductResponseDto;
import com.example.hockeyshop.models.Product;
import com.example.hockeyshop.repositories.ProductsRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsService {

    private ModelMapper modelMapper;
    private ProductsRepository productsRepository;

    public List<ProductResponseDto> getAll(){
        List<Product> products = productsRepository.findAll();

        return products.stream().map(product -> modelMapper
                .map(product,ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> getByCategoryId(int categoryId){
        List<Product> products = productsRepository.findAllByCategoryId(categoryId);

        return products.stream().map(product -> modelMapper
                        .map(product,ProductResponseDto.class))
                .collect(Collectors.toList());
    }
}
