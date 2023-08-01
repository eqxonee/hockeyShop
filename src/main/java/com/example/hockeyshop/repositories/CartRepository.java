package com.example.hockeyshop.repositories;

import com.example.hockeyshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findCartByUserIdAndProductId(int userId, int productId);

    List<Cart> findAllByUserId(int userId);

    void deleteByUserId(int userId);
}
