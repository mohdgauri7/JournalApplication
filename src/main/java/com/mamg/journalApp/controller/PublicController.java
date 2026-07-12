package com.mamg.journalApp.controller;

import com.mamg.journalApp.entity.User;
import com.mamg.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "okay";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> creatUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveNewUser(user), HttpStatus.CREATED);
    }
}
