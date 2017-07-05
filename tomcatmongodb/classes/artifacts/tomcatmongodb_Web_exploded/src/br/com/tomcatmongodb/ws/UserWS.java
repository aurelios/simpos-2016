package br.com.tomcatmongodb.ws;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.MongoClient;

import br.com.tomcatmongodb.dao.UserDAO;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserWS {
    
    @GET
    @Path("/getAll")    
    public List<String> WSChecarVersao() {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient( "localhost" , 27017 );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        UserDAO userDAO = new UserDAO(mongo);
        return userDAO.readAllUsers();//Response.status(200).entity(userDAO.readAllUsers()).build();

    }
}
