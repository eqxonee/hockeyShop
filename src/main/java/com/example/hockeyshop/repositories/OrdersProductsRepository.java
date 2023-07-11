package com.example.hockeyshop.repositories;

import com.example.hockeyshop.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersProductsRepository extends JpaRepository<OrderProduct, Integer> {
}
