package org.arkadipta.journalbuddy.controller;

import lombok.extern.slf4j.Slf4j;
import org.arkadipta.journalbuddy.entity.JournalEntry;
import org.arkadipta.journalbuddy.entity.User;
import org.arkadipta.journalbuddy.services.JournalEntryService;
import org.arkadipta.journalbuddy.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/journal")
public class JournalBuddyController {

    @Autowired
    public JournalEntryService journalEntryService;

    @Autowired
    public UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllEntriesForUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if (journalEntries != null && !journalEntries.isEmpty()){
            return new ResponseEntity<>(journalEntries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){
        try {
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/entry{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userName}/{myId}")
    public  ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryService.deleteEntryById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(
            @PathVariable("myId") ObjectId myId,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName) {

        JournalEntry old = journalEntryService.getEntryById(myId).orElse(null);
        if (old != null) {
            // Update only if the new title is valid
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());

            old.setBody(newEntry.getBody() != null && !newEntry.getBody().isEmpty() ? newEntry.getBody() : old.getBody());

            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
