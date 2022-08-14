package com.example.amazonclone.Controllers;

import com.example.amazonclone.Service.CategoryService;
import com.example.amazonclone.Service.ProductService;
import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/amazon")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/products")
    public ResponseEntity getProductservice() {
        ArrayList<Product> productlist = productService.getproducts();
        return ResponseEntity.status(200).body(productlist);
    }

    @PostMapping("/registerproducts")
    public ResponseEntity addproducts(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        productService.addproducts(product);
        return ResponseEntity.status(201).body(new ApiResponse("New product added !", 201));
    }

    @PutMapping("/products/{index}")
    public ResponseEntity updateproduct(@RequestBody @Valid Product product
            , @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }

        productService.UpdateProduct(index, product);
        return ResponseEntity.status(201).body(new ApiResponse("product updated !", 201));
    }

    @DeleteMapping("/products/{index}")
    public ResponseEntity deleteproducts(@PathVariable int index) {
        productService.deleteProduct(index);
        return ResponseEntity.status(200).body(new ApiResponse("product deleted !", 200));
    }

    @PostMapping("/buy")
    public ResponseEntity buyproduct(@RequestBody String ID, @RequestBody String producid, @RequestBody String merchantid) {
        Integer productCase = productService.buyproduct(ID, producid, merchantid);
        switch (productCase) {
            case -1:
                return ResponseEntity.status(400).body(new ApiResponse("User id or merchant id is wrong",400));
            case 0:
                return ResponseEntity.status(400).body(new ApiResponse("You don't have enough money for the product",400));
            case 1:
                return ResponseEntity.status(400).body(new ApiResponse(" product unavailable",400));
            case 2:
                return ResponseEntity.status(200).body(new ApiResponse("product purchased !",400));
            default:
                return ResponseEntity.status(500).body(new ApiResponse("Server error !",400));
        }


    }
}
