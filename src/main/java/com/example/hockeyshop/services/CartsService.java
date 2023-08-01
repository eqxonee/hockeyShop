package com.example.hockeyshop.services;


import com.example.hockeyshop.dtos.InsertToCartDto;
import com.example.hockeyshop.dtos.ShowUserCartDto;
import com.example.hockeyshop.models.Cart;
import com.example.hockeyshop.models.Product;
import com.example.hockeyshop.models.User;
import com.example.hockeyshop.repositories.CartRepository;
import com.example.hockeyshop.repositories.ProductsRepository;
import com.example.hockeyshop.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartsService {
    private CartRepository cartsRepository;
    private ProductsRepository productsRepository;
    private UsersRepository usersRepository;
    private ModelMapper modelMapper;

    public void addNew(InsertToCartDto insertToCartDto) {
        Optional<Cart> cart = cartsRepository.findCartByUserIdAndProductId(insertToCartDto.getUserId(), insertToCartDto.getProductId());

        if (cart.isPresent()) {

            Cart foundCart = cart.get();

            foundCart.setAmount(foundCart.getAmount() + 1);
            cartsRepository.save(foundCart);
        } else {
            User user = usersRepository.findById(insertToCartDto.getUserId()).get();
            Product product = productsRepository.findById(insertToCartDto.getProductId()).get();

            Cart insertedCart = Cart.builder()
                    .amount(1)
                    .user(user)
                    .product(product)
                    .build();

            cartsRepository.save(insertedCart);
        }

    }

    public List<ShowUserCartDto> getCartByUserId(int userId) {
        List<Cart> cartItems = cartsRepository.findAllByUserId(userId);

        return cartItems.stream()
                .map(item -> modelMapper.map(item, ShowUserCartDto.class))
                .collect(Collectors.toList());
    }
}
