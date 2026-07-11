package com.mamg.journalApp.repository;

import com.mamg.journalApp.entity.JournalEntry;
import com.mamg.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);


}
