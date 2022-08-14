package com.example.amazonclone.Service;

import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.Merchant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class MerchantService {
    private ArrayList<Merchant> merchants=new ArrayList<>();

    public  ArrayList<Merchant> getMerchants(){
        return merchants;
    }

    public void addmerchants(Merchant merchant){
        merchants.add(merchant);
    }

    public void Updatemerchants(int index,Merchant merchant){
        if(index>=merchants.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        merchants.set(index,merchant);
    }

    public void deletemerchants(int index) {
        if(index>=merchants.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        merchants.remove(index);
    }
}
