package org.arkadipta.journalbuddy.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journalEntry")
@Data // (is equivalent of @Getter @Setter @ToString and 2 more)
public class JournalEntry {

    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String body;

    private LocalDateTime date;

}
