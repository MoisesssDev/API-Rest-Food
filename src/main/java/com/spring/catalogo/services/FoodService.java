package com.spring.catalogo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.spring.catalogo.model.Food;

public interface FoodService {
	
	List<Food> findAll();
	
	Optional<Food> findById( UUID id);
	
	Food save(Food food);
	
	void delete(Food food);
}
