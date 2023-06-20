package com.spring.catalogo.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.catalogo.dtos.FoodDto;
import com.spring.catalogo.model.Food;
import com.spring.catalogo.services.FoodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/foods")
public class FoodController {
	@Autowired
	FoodService foodService;
	
	@PostMapping
	public ResponseEntity<Food> saveFood(@RequestBody @Valid FoodDto foodDto){
		var newFood = new Food();
		BeanUtils.copyProperties(foodDto, newFood);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(foodService.save(newFood));
	}
	
	@GetMapping
	public ResponseEntity<List<Food>> getAll(){
		List<Food> foods = foodService.findAll();
		
		if(!foods.isEmpty()) {
			for (Food food : foods) {
				UUID id = food.getId();
				food.add(linkTo(methodOn(FoodController.class).getOne(id)).withSelfRel());
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(foodService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@PathVariable(value="id") UUID id){
		Optional<Food> food = foodService.findById(id);
		
		if(food.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		
		food.get().add(linkTo(methodOn(FoodController.class).getAll()).withRel("Food List"));
		return ResponseEntity.status(HttpStatus.OK).body(food.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateFood(@PathVariable(value = "id") UUID id, @RequestBody @Valid FoodDto food){
		Optional<Food> foundFood = foodService.findById(id);
		
		if(foundFood.isEmpty()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		
		BeanUtils.copyProperties(food, foundFood.get());
		return ResponseEntity.status(HttpStatus.OK).body(foodService.save(foundFood.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
		Optional<Food> food = foodService.findById(id);
		
		if(food.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		
		foodService.delete(food.get());
		return ResponseEntity.status(HttpStatus.OK).body("Food deleted sucessfully.");
	}
	
}
