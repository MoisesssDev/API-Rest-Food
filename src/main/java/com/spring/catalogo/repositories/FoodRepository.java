package com.spring.catalogo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.catalogo.model.Food;

public interface FoodRepository extends JpaRepository<Food, UUID>{

}
