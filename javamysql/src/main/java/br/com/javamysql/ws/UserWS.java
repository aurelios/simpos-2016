package br.com.javamysql.ws;


import br.com.javamysql.dao.PersonDAO;
import br.com.javamysql.model.Person;

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
        PersonDAO personDAO = new PersonDAO();
        return personDAO.readAllUsers();

    }
}
