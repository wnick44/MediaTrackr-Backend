package com.mediatrackr;
import javax.ws.rs.ApplicationPath;

import jakarta.inject.Inject;

@ApplicationPath("/api")
public class ComicService {
    @Inject SharedOntopiaEngine ontopiaEngine;
}
