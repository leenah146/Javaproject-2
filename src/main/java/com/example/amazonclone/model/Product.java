package com.example.amazonclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Product {
   @NotEmpty(message = "ID cannot be empty")
    @Size(min=3, message ="ID must be longer than 2 characters")
    private String ID;
    @NotEmpty(message = "name cannot be empty")
    @Size(min=3, message ="name must be longer than 2 characters")
    private String name;
    @NotNull(message = "price cannot be empty")
    @PositiveOrZero(message = "can't be negative")
    private int price;
   @NotEmpty(message = "ID cannot be empty")
   @Size(min=3, message ="ID must be longer than 2 characters")
    private String categoryID;

}
