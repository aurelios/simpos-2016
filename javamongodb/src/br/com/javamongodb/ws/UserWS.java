package br.com.javamongodb.ws;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.MongoClient;

import br.com.javamongodb.dao.PersonDAO;

@Path("/personrest")
@Produces(MediaType.APPLICATION_JSON)
public class UserWS {
    
    @GET
    public String WSChecarVersao() {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient( "localhost" , 27017 );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        PersonDAO userDAO = new PersonDAO(mongo);
        return userDAO.readAllUsers().toString();

    }
}
