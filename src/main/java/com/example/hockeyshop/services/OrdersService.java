package com.example.hockeyshop.services;


import com.example.hockeyshop.dtos.RequestOrderDto;
import com.example.hockeyshop.models.Cart;
import com.example.hockeyshop.models.Order;
import com.example.hockeyshop.models.OrderProduct;
import com.example.hockeyshop.models.User;
import com.example.hockeyshop.repositories.CartRepository;
import com.example.hockeyshop.repositories.OrdersProductsRepository;
import com.example.hockeyshop.repositories.OrdersRepository;
import com.example.hockeyshop.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdersService {

    private CartRepository cartsRepository;
    private OrdersRepository ordersRepository;
    private OrdersProductsRepository ordersProductsRepository;
    private UsersRepository usersRepository;

    @Transactional
    public void makeOrder(RequestOrderDto OrderRequestDto) {
        int userId = OrderRequestDto.getUserId();
        User user = usersRepository.findById(userId).get();

        List<Cart> cartItems = cartsRepository.findAllByUserId(userId);

        BigDecimal totalPrice = new BigDecimal(0);
        for (Cart cartItem : cartItems) {
            totalPrice = totalPrice.add(cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getAmount())));
        }

        Order insertedOrder = Order.builder()
                .datetime(LocalDateTime.now())
                .user(user)
                .totalPrice(totalPrice)
                .build();
        ordersRepository.save(insertedOrder);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (Cart cartItem : cartItems) {
            orderProducts.add(
                    OrderProduct.builder()
                            .order(insertedOrder)
                            .product(cartItem.getProduct())
                            .amount(cartItem.getAmount())
                            .build()
            );
        }
        ordersProductsRepository.saveAll(orderProducts);

        cartsRepository.deleteByUserId(userId);
    }
}
