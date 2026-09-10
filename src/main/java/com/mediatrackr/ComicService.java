package com.mediatrackr;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mediatrackr.dao.Books;
import com.mediatrackr.dao.Comics;

import jakarta.inject.Inject;

@ApplicationPath("/comic")
public class ComicService {
    @Inject SharedOntopiaEngine ontopiaEngine;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComic(Comics comic){
        boolean success = ontopiaEngine.addMedia(comic);
        if(success){
            return Response.ok().entity("added successfully").build();
        }
        else{
            return Response.serverError().entity("Server is down").build();
        }
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComic(@PathParam("id") int id){

    }
}
