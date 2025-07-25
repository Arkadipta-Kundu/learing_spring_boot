package org.arkadipta.journalbuddy.controller;

import org.arkadipta.journalbuddy.entity.JournalEntry;
import org.arkadipta.journalbuddy.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalBuddyController {

    @Autowired
    public JournalEntryService journalEntryService;

//    @GetMapping("/home")
//    public String welcome(){
//        return "welcome writer !";
//    }
    @GetMapping
    public List<JournalEntry> getall(){
        return journalEntryService.showAll();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            return new ResponseEntity<>(journalEntryService.saveEntry(myEntry), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{myId}")
    public  ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteEntryById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{myId}")
    public JournalEntry updataJournalEntryById(@PathVariable("myId") ObjectId myId, @RequestBody JournalEntry newEntry) {
    return journalEntryService.updateEntryById(myId,newEntry);
    }
}
