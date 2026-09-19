package com.mediatrackr;
import java.io.ByteArrayOutputStream;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import com.mediatrackr.dao.Books;
import com.mediatrackr.dao.Comics;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import net.ontopia.topicmaps.query.core.InvalidQueryException;

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
    public Response getComic(@PathParam("id") int id) throws InvalidQueryException, JAXBException{
        String tolog = "";

        Comics media = (Comics)ontopiaEngine.getMedia(tolog);

        JAXBContext ctx = JAXBContext.newInstance(Books.class);
        Marshaller marshaller = ctx.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
        
        ByteArrayOutputStream jsonString = new ByteArrayOutputStream();
        marshaller.marshal(media, jsonString);

        return Response.ok(jsonString.toString()).build();
    }
}
