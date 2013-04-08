package com.onsalelocal.target.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.target.service.TargetService;
import common.util.web.GenericResponse;
import common.util.web.JerseyBaseResource;

@Path("/ws/target")
@Component
public class TargetResource extends JerseyBaseResource {
    @Autowired private TargetService targetService;

    @GET
    @Path("/stores")
    public StoreList listStores() {
    	return targetService.listStores();
    }
    
    @GET
    @Path("/fetch-stores")
    public GenericResponse fetchStores() {
    	targetService.fetchStores();
    	return new GenericResponse();
    }
    
    @Path("geo")
    @GET
    public GenericResponse geo() {
    	targetService.geo();
    	return new GenericResponse();
    }
}
