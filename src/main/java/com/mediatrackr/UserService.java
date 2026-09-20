package com.mediatrackr;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Response;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mediatrackr.dao.User;

@ApplicationPath("/user")
public class UserService {
    @Inject
    SharedLDAPEngine engine;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user){
        boolean success = engine.createUser(user);

        if(success){
            return Response.ok().build();
        }
        else{
            return Response.status(404).build();
        }
    }

    @GET
    @Path("{username}-{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUser(String username, String password){
        String JWT = engine.findUser(username, password);
        if(JWT.isEmpty()){
            return Response.status(404).build();
        }
        else{
            return Response.ok(JWT).build();
        }
    }

}
