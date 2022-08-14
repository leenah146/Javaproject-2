package com.example.amazonclone.Controllers;

import com.example.amazonclone.Service.CategoryService;
import com.example.amazonclone.Service.ProductService;
import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.Category;
import com.example.amazonclone.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/amazon")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @GetMapping("/categories")
    public ResponseEntity getcategories() {
        ArrayList<Category> categorylist = categoryService.getcategories();
        return ResponseEntity.status(200).body(categorylist);
    }

    @PostMapping("/registercategories")
    public ResponseEntity addcategories(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        categoryService.addcategories(category);
        return ResponseEntity.status(201).body(new ApiResponse("New category added !", 201));
    }

    @PutMapping("/categories/{index}")
    public ResponseEntity updatecategories(@RequestBody @Valid Category category
            , @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }

       categoryService.UpdateCategory(index, category);
        return ResponseEntity.status(201).body(new ApiResponse("category updated !", 201));
    }

    @DeleteMapping("/categories/{index}")
    public ResponseEntity deletecategories(@PathVariable int index){
        categoryService.deletecategories(index);
        return ResponseEntity.status(200).body(new ApiResponse("category deleted !",200));
    }
}
