package org.arkadipta.journalbuddy.services;

import lombok.extern.slf4j.Slf4j;
import org.arkadipta.journalbuddy.Repository.UserRepo;
import org.arkadipta.journalbuddy.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveEntry(User user){
        try{
            userRepo.save(user);
        }catch (Exception e){
            log.error("Error caused by this{}", String.valueOf(e));
        }
    }

    public void saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        } catch (Exception e) {
            log.error("Error caused by this{}", String.valueOf(e));
        }
    }

    public void saveNewAdminUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER","ADMIN"));            userRepo.save(user);
        } catch (Exception e) {
            log.error("Error caused by this{}", String.valueOf(e));
        }
    }

    public List<User> showAll(){
        return userRepo.findAll();
    }

    public Optional<User> getEntryById(ObjectId id) {
        return userRepo.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        userRepo.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepo.getUserByUserName(userName);
    }
}