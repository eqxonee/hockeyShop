package com.example.hockeyshop.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private int id;
    private String name;
    private int rigidity;
    private String grip;
    private BigDecimal price;
    private int countryId;
    private String countryName;
    private String description;
    private String bend;
    private String picturePath;
    private int categoryId;
    private String categoryName;
}
