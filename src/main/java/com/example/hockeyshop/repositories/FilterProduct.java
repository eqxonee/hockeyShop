package com.example.hockeyshop.repositories;

import com.example.hockeyshop.models.Product;
import com.example.hockeyshop.services.FilService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterProduct extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM products WHERE name =:name AND stick_grip =:stick_grip AND stick_bend =:stick_bend",nativeQuery = true)
    List<Product> findAllByFilter(@Param("name") String name,@Param("stick_grip") String stick_grip,@Param("stick_bend") String stick_bend);


}
