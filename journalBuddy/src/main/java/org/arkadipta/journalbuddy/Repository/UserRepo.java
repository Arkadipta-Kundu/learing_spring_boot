package org.arkadipta.journalbuddy.Repository;

import org.arkadipta.journalbuddy.entity.JournalEntry;
import org.arkadipta.journalbuddy.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
    User getUserByUserName(String userName);
}
