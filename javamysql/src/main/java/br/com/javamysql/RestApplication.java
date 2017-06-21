package br.com.javamysql;


import br.com.javamysql.ws.UserWS;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class RestApplication extends Application{
    private Set<Object> singletons = new HashSet<Object>();

    public RestApplication() {
        singletons.add(new UserWS());
    }
    
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
