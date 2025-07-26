package org.arkadipta.journalbuddy.services;

import lombok.extern.slf4j.Slf4j;
import org.arkadipta.journalbuddy.Repository.UserRepo;
import org.arkadipta.journalbuddy.entity.JournalEntry;
import org.arkadipta.journalbuddy.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    public void saveEntry(User user){
         try{
             userRepo.save(user);
         }catch (Exception e){
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

//    public JournalEntry updateEntryById(ObjectId id, JournalEntry newEntry) {
//        JournalEntry oldEntry = userRepo.findById(id).orElse(null);
//        if (oldEntry != null) {
//            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
//                oldEntry.setTitle(newEntry.getTitle());
//            }
//            if (newEntry.getBody() != null && !newEntry.getBody().isEmpty()) {
//                oldEntry.setBody(newEntry.getBody());
//            }
//            if (newEntry.getDate() != null) {
//                oldEntry.setDate(newEntry.getDate());
//            }
//            return userRepo.save(oldEntry);
//        }
//        return null;
//    }

}
