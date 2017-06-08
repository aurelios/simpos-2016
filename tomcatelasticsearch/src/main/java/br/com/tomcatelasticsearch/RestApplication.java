package br.com.tomcatelasticsearch;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.tomcatelasticsearch.ws.UserWS;

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
