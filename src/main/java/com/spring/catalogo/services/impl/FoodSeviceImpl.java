package com.spring.catalogo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.catalogo.model.Food;
import com.spring.catalogo.repositories.FoodRepository;
import com.spring.catalogo.services.FoodService;

@Service
public class FoodSeviceImpl implements FoodService{
	
	@Autowired
	FoodRepository foodRepository;

	@Override
	public List<Food> findAll() {
		return foodRepository.findAll();
	}

	@Override
	public Optional<Food> findById(UUID id) {
		return foodRepository.findById(id);
	}

	@Override
	public Food save(Food food) {
		return foodRepository.save(food);
	}

	@Override
	public void delete(Food food) {
		foodRepository.delete(food);
	}
	
}
