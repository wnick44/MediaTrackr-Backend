package com.mediatrackr;

import javax.ws.rs.ApplicationPath;

import jakarta.inject.Inject;

@ApplicationPath("/book")
public class BookService {
    @Inject SharedOntopiaEngine ontopiaEngine;
}
