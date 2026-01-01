package net.digest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.digest.journalApp.entity.User;
import net.digest.journalApp.service.UserService;



@RestController
@RequestMapping("/public")
public class PublicController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK"; 
    }

    @PostMapping("/create-user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        boolean saved = userService.saveNewUser(user);
        if(saved) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}