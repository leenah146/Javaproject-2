package com.example.amazonclone.Service;

import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private ArrayList<User> Userlist=new ArrayList<>();

    public  ArrayList<User> getUsers(){
        return Userlist;
    }

    public User getUser(String userid) {
        for (User user:Userlist) {
            if(user.getID().equals(userid)){
                return user;
            }
        }
        return null;
    }

    public String getRole() {
        for (User user:Userlist) {
                return user.getRole();
            }
        return null;
        }



    public boolean addUser(User users){
       return Userlist.add(users);
    }

    public void UpdateUsers(int index,User user){
        if(index>=Userlist.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
       Userlist.set(index,user);
    }

    public void deleteUser(int index) {
        if(index>=Userlist.size()){
            ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        Userlist.remove(index);
    }
}
