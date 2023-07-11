package com.example.hockeyshop.repositories;

import com.example.hockeyshop.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<Country, Integer> {
}
