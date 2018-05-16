package br.com.javamongodb.ws;

import br.com.javamongodb.dao.PersonDAO;
import com.mongodb.MongoClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;
import java.util.List;

@Path("/personrest")
@Produces(MediaType.APPLICATION_JSON)
public class UserWS {
    
    @GET
    public List<String> WSChecarVersao() {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient( "localhost" , 27017 );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        PersonDAO userDAO = new PersonDAO(mongo);
        return userDAO.readAllUsers();

    }
}
