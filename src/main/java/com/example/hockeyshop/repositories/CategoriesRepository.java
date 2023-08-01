package com.example.hockeyshop.repositories;

import com.example.hockeyshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByOrderByIdAsc();
}
