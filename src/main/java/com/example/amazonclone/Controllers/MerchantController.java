package com.example.amazonclone.Controllers;

import com.example.amazonclone.Service.CategoryService;
import com.example.amazonclone.Service.MerchantService;
import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.Category;
import com.example.amazonclone.model.Merchant;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/amazon")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {

        this.merchantService=merchantService;

    }

    @GetMapping("/merchants")
    public ResponseEntity getmerchants() {
        ArrayList<Merchant> merchantslist = merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchantslist);
    }

    @PostMapping("/registermerchants")
    public ResponseEntity addmerchants(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        merchantService.addmerchants(merchant);
        return ResponseEntity.status(201).body(new ApiResponse("New merchant added !", 201));
    }

    @PutMapping("/merchants/{index}")
    public ResponseEntity updatemerchants(@RequestBody @Valid Merchant merchant
            , @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }

       merchantService.Updatemerchants(index, merchant);
        return ResponseEntity.status(201).body(new ApiResponse("merchant updated !", 201));
    }

    @DeleteMapping("/merchants/{index}")
    public ResponseEntity deletemerchants(@PathVariable int index){
       merchantService.deletemerchants(index);
        return ResponseEntity.status(200).body(new ApiResponse("merchant deleted !",200));
    }
}
