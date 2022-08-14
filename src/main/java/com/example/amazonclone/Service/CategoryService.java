package com.example.amazonclone.Service;

import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    private ArrayList<Category> categories=new ArrayList<>();

    public  ArrayList<Category> getcategories(){
        return categories;
    }

    public void addcategories(Category category){
        categories.add(category);
    }

    public void UpdateCategory(int index,Category category){
        if(index>=categories.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        categories.set(index,category);
    }

    public void deletecategories(int index) {
        if(index>=categories.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
       categories.remove(index);
    }

}
