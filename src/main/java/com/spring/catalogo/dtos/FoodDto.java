package com.spring.catalogo.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FoodDto(@NotEmpty String title, @NotEmpty String image, @NotNull BigDecimal price) {
	
}
