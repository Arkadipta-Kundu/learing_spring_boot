package org.arkadipta.journalbuddy.controller;

import lombok.extern.slf4j.Slf4j;
import org.arkadipta.journalbuddy.entity.User;
import org.arkadipta.journalbuddy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    @GetMapping("/get-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> allUsers = userService.showAll();
        if (allUsers != null && !allUsers.isEmpty()){
            return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/create-new-admin")
    public void createAdmin(@RequestBody User user){
        userService.saveNewAdminUser(user);
    }

}