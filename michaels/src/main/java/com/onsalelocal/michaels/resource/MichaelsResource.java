package com.onsalelocal.michaels.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.michaels.service.MichaelsService;

import common.util.web.GenericResponse;
import common.util.web.JerseyBaseResource;

@Path("/ws/michaels")
@Component
public class MichaelsResource extends JerseyBaseResource {
    @Autowired private MichaelsService michaelsService;

    @GET
    @Path("/stores")
    public StoreList listStores() {
    	return michaelsService.listStores();
    }
    
    @GET
    @Path("/fetch-stores")
    public GenericResponse fetchStores() {
    	michaelsService.fetchStores();
    	return new GenericResponse();
    }
}
