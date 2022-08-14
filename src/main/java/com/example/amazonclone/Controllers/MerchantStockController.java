package com.example.amazonclone.Controllers;

import com.example.amazonclone.Service.MerchantService;
import com.example.amazonclone.Service.MerchantStockService;
import com.example.amazonclone.Service.ProductService;
import com.example.amazonclone.Service.UserService;
import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.Merchant;
import com.example.amazonclone.model.MerchantStock;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/amazon")
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    private final UserService userService;
    public MerchantStockController(MerchantStockService merchantStockService, UserService userService) {

        this.merchantStockService=merchantStockService;
        this.userService=userService;

    }

    @GetMapping("/stock")
    public ResponseEntity getmerchantstock() {
        ArrayList<MerchantStock> stocklist = merchantStockService.getstocks();
        return ResponseEntity.status(200).body(stocklist);
    }

    @PostMapping("/registerstock")
    public ResponseEntity addmerchantstock(@RequestBody @Valid MerchantStock stock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        merchantStockService.addstock(stock);
        return ResponseEntity.status(201).body(new ApiResponse("New stock added !", 201));
    }

    @PutMapping("/stock/{index}")
    public ResponseEntity updatemerchantstock(@RequestBody @Valid MerchantStock stock
            , @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }

      merchantStockService.Updatemerchantstock(index, stock);
        return ResponseEntity.status(201).body(new ApiResponse("stock updated !", 201));
    }

    @DeleteMapping("/stock/{index}")
    public ResponseEntity deletestock(@PathVariable int index){
       merchantStockService.deletemerchantstock(index);
        return ResponseEntity.status(200).body(new ApiResponse("stock deleted !",200));
    }
    @PostMapping("/addproduct")
    public ResponseEntity adduserproduct(@RequestBody String ID,@RequestBody String producid,@RequestBody String merchantid){
        boolean isValid=merchantStockService.useraddproduct(ID,producid,merchantid);
        if(isValid){
            return ResponseEntity.status(200).body(new ApiResponse("stock added!",200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid user",400));

    }

}
