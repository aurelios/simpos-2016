package br.com.tomcatelasticsearch.ws;

import br.com.tomcatelasticsearch.dao.PersonDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/personrest")
@Produces(MediaType.APPLICATION_JSON)
public class UserWS {

    @GET
    public String WSChecarVersao() {
        PersonDAO personDAO = new PersonDAO();
        return personDAO.readAllUsers().toString();

    }
}
