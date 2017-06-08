package br.com.tomcatelasticsearch.ws;

import br.com.tomcatelasticsearch.dao.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserWS {
    
    @GET
    @Path("/getAll")    
    public String WSChecarVersao() {
        UserDAO userDAO = new UserDAO();
        return userDAO.readAllUsers().toString();

    }
}
