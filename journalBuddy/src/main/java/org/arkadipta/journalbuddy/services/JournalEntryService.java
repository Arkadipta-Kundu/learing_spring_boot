package org.arkadipta.journalbuddy.services;

import lombok.extern.slf4j.Slf4j;
import org.arkadipta.journalbuddy.Repository.JournalEntryRepo;
import org.arkadipta.journalbuddy.entity.JournalEntry;
import org.arkadipta.journalbuddy.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JournalEntryService {

    @Autowired
    public JournalEntryRepo journalEntryRepo;

    @Autowired
    public UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry , String userName){
         try{
             User user = userService.findByUserName(userName);
             journalEntry.setDate(LocalDateTime.now());
             JournalEntry saved = journalEntryRepo.save(journalEntry);
             user.getJournalEntries().add(saved);
             userService.saveEntry(user);
         }catch (Exception e){
             log.error("Error caused by this{}", String.valueOf(e));
         }
    }

    public void saveEntry(JournalEntry journalEntry ){
        try{
            journalEntryRepo.save(journalEntry);
        }catch (Exception e){
            log.error("Error caused by this{}", String.valueOf(e));
        }
    }

    public List<JournalEntry> showAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
         return journalEntryRepo.findById(id);
    }

    public void deleteEntryById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        userService.saveEntry(user);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        journalEntryRepo.deleteById(id);
    }



}
