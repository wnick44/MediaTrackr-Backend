package com.mediatrackr;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Response;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationPath("/user")
public class UserService {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user){

    }
}
