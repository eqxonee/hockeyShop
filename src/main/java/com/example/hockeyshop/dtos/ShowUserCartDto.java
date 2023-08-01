package com.example.hockeyshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowUserCartDto {
    private int productId;
    private String productName;
    private BigDecimal productPrice;
    private int amount;
}
