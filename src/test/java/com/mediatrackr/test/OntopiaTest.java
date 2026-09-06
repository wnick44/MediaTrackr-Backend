package com.mediatrackr.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mediatrackr.SharedOntopiaEngine;
import com.mediatrackr.dao.Books;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

public class OntopiaTest {
   EmbeddedPostgres postgres;
   SharedOntopiaEngine engine;

   @BeforeAll
    public void startPostgres() throws IOException{
        postgres = EmbeddedPostgres.builder()
                    .setPort(5432)
                    
                    .start();
        engine = new SharedOntopiaEngine();
    }

    @Test
    @DisplayName("Addition works correctly")
    public void addMediaTest(){
        Books book = new Books();
        book.setTitle("IT");

        book.setAuthor("Stephen King");
        book.setISBN("9781501182099");

        book.setFormat("Print");
        book.setPublisher("Berkley");
        
        boolean result = engine.addMedia(book);
        assertTrue(result);
        
    }
}
