package br.com.javapostgres.ws;


import br.com.javapostgres.dao.UserDAO;
import br.com.javapostgres.model.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserWS {
    
    @GET
    @Path("/getAll")    
    public List<Person> WSChecarVersao() {
        UserDAO userDAO = new UserDAO();
        return userDAO.readAllUsers();

    }
}
