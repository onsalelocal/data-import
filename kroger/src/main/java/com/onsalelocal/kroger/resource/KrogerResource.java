package com.onsalelocal.kroger.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.kroger.service.KrogerService;
import common.util.web.GenericResponse;
import common.util.web.JerseyBaseResource;

@Path("/ws/kroger")
@Component
public class KrogerResource extends JerseyBaseResource {
    @Autowired private KrogerService krogerService;

    @GET
    @Path("/stores")
    public StoreList listStores() {
    	return krogerService.listStores();
    }
    
    @GET
    @Path("/fetch-stores")
    public GenericResponse fetchStores() {
    	krogerService.fetchStores();
    	return new GenericResponse();
    }
}
