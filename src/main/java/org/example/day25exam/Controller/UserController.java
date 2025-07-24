package org.example.day25exam.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.day25exam.Api.ApiResponse;
import org.example.day25exam.Model.User;
import org.example.day25exam.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService service;

    @GetMapping("/get")
    public ResponseEntity<?> getUser(){
      return ResponseEntity.ok(service.getUsers());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, Errors err){
        if(err.hasErrors()){
            return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));
        }
        if(service.addUser(user)){
            return ResponseEntity.ok(new ApiResponse("User Added Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("User ID Already Added"));
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, Errors err){
        if(err.hasErrors())
            return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));

        if(service.updateUser(user)){
            return ResponseEntity.ok(new ApiResponse("User Updated Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("User ID Not Found"));
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<?> deleteUser(@PathVariable String ID){
        if(service.deleteUser(ID)){
            return ResponseEntity.ok(new ApiResponse("User Deleted Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("User ID Not Found"));
    }

    @GetMapping("/balance/same/{balance}")
    public ResponseEntity<?> getSameBalance(@PathVariable double balance){
        if(balance < 0){
            return ResponseEntity.badRequest().body(new ApiResponse("Balance should Be Only Positive"));
        }
        ArrayList<?> same = service.getUsersWithBalance(balance);
        if(same.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("No User With the same Balance"));
        }
        return ResponseEntity.ok(same);
    }
    @GetMapping("/age/same/{age}")
    public ResponseEntity<?> getSameAge(@PathVariable int age){
        if(age < 0){
            return ResponseEntity.badRequest().body(new ApiResponse("age should Be Only Positive"));
        }
        ArrayList<?> same = service.getUsersWithSameAge(age);
        if(same.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("No User With the same age"));
        }
        return ResponseEntity.ok(same);
    }


}
