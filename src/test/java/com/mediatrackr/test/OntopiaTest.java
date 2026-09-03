package com.mediatrackr.test;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

public class OntopiaTest {
   private EmbeddedPostgres postgres;

   @BeforeAll
    public void startPostgres() throws IOException{
        postgres = EmbeddedPostgres.builder()
                    .setPort(0) // 0 = random available port
                    .start();
    }

    @Test
    public void addMediaTest(){
        
    }
}
