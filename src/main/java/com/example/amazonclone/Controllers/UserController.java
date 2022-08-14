package com.example.amazonclone.Controllers;
import com.example.amazonclone.Service.UserService;
import com.example.amazonclone.model.ApiResponse;
import com.example.amazonclone.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/amazon")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

       this.userService=userService;

    }

    @GetMapping("/users")
    public ResponseEntity getusers() {
        ArrayList<User> userlist = userService.getUsers();
        return ResponseEntity.status(200).body(userlist);
    }

    @PostMapping("/registerusers")
    public ResponseEntity addusers(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse("New user added !", 201));
    }

    @PutMapping("/users/{index}")
    public ResponseEntity updateusers(@RequestBody @Valid User user
            , @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }

        userService.UpdateUsers(index, user);
        return ResponseEntity.status(201).body(new ApiResponse("user updated !", 201));
    }

    @DeleteMapping("/users/{index}")
    public ResponseEntity deletemerchants(@PathVariable int index){
        userService.deleteUser(index);
        return ResponseEntity.status(200).body(new ApiResponse("merchant deleted !",200));
    }
}
