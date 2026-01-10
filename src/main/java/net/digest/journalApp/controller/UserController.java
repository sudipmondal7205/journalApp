package net.digest.journalApp.controller;

import net.digest.journalApp.api.response.WeatherResponse;
import net.digest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import net.digest.journalApp.entity.User;
import net.digest.journalApp.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userServices;

    @Autowired
    private WeatherService weatherService;

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userServices.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userServices.saveNewUser(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete-user")
    public ResponseEntity<HttpStatus> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userServices.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        WeatherResponse weather = weatherService.getWeather("Mumbai");
        String greeing = "";
        if (weather != null) {
            greeing = ",\n Weather feels like " + weather.getCurrent().getTempC();
        }
        return new ResponseEntity<>("Hi " + name + greeing, HttpStatus.OK);
    }
}
