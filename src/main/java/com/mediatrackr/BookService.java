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

import jakarta.inject.Inject;

@ApplicationPath("/book")
public class BookService {
    @Inject SharedOntopiaEngine ontopiaEngine;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Books book){
        boolean success = ontopiaEngine.addMedia(book);
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
    public Response getBook(@PathParam("id")int id){

    }
}
