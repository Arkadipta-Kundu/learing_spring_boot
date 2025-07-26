package org.arkadipta.journalbuddy.controller;

import lombok.extern.slf4j.Slf4j;

import org.arkadipta.journalbuddy.entity.User;
import org.arkadipta.journalbuddy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.showAll();
    }
    @PostMapping
    public void saveUsers(@RequestBody User user){
        userService.saveEntry(user);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUsers(@RequestBody User user,@PathVariable String userName){
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
