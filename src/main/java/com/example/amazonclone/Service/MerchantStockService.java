package com.example.amazonclone.Service;

import com.example.amazonclone.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final UserService userService;


    private ArrayList<MerchantStock> stocklist=new ArrayList<>();
    public  ArrayList<MerchantStock> getstocks(){
        return stocklist;
    }

    public void addstock(MerchantStock stock){
        stocklist.add(stock);
    }
    public MerchantStock getproductid(String productid) {
        for (MerchantStock product:stocklist) {
            if(product.getID().equals(productid)){
                return product;
            }
        }
        return null;
    }

    public void Updatemerchantstock(int index,MerchantStock stock){
        if(index>=stocklist.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        stocklist.set(index,stock);
    }

    public void deletemerchantstock(int index) {
        if(index>=stocklist.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        stocklist.remove(index);
    }

    public boolean useraddproduct(String userID,String producid,String merchantid){
       User user=userService.getUser(userID);
       MerchantStock merchantStock=getproductid(producid);
       if(user==null&&merchantid==null){
           return false;
       }if(userService.getRole().equals("Admin")){
            merchantStock.setStock(merchantStock.getStock()+1);
            return true;
           }
        return false;
        }


    }

