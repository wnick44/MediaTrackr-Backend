package com.mediatrackr;
import javax.ws.rs.ApplicationPath;

import jakarta.inject.Inject;

@ApplicationPath("/comic")
public class ComicService {
    @Inject SharedOntopiaEngine ontopiaEngine;
}
