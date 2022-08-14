package com.example.amazonclone.Service;

import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.MerchantStock;
import com.example.amazonclone.model.Product;
import com.example.amazonclone.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final UserService userService;
    private final MerchantStockService merchantStockService;

    private ArrayList<Product> products= new ArrayList<>();

    public  ArrayList<Product> getproducts(){
        return products;
    }

    public Product getproductid(String productid) {
        for (Product product:products) {
            if(product.getID().equals(productid)){
                return product;
            }
        }
        return null;
    }

    public void addproducts(Product product){
        products.add(product);

    }

    public void UpdateProduct(int index,Product product){
        if(index>=products.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        products.set(index,product);
    }

    public void deleteProduct(int index) {
        if(index>=products.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        products.remove(index);
    }
    public int buyproduct(String userID,String producid,String merchantid) {
        User user=userService.getUser(userID);
        Product product=getproductid(producid);
        MerchantStock merchantStock=merchantStockService.getproductid(merchantid);

        if(userID==null || producid ==null||merchantid==null){
            return -1;
        }

        if(user.getBalance()<product.getPrice()){
            return 0;
        }

        if(merchantStock.getStock()==0){
            return 1;
        }

       int oldStock=merchantStock.getStock();
        merchantStock.setStock(oldStock-1);

        int oldBalance=user.getBalance();
        user.setBalance(oldBalance-product.getPrice());


        return 2;

    }

}
