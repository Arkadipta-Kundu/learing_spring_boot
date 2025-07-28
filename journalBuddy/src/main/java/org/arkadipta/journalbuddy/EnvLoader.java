package org.arkadipta.journalbuddy;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    static {
        Dotenv dotenv = Dotenv.configure()
                .directory("./")  // Make sure this points to where your .env file is
                .ignoreIfMissing()
                .load();

        // Copy values to system properties so Spring can use them
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}

