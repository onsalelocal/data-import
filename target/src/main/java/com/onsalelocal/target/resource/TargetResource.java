package com.onsalelocal.target.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

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
    
    @GET
    @Path("/fetch-offers")
    public GenericResponse fetchOffers(@QueryParam("city") String city, @QueryParam("state") String state, @QueryParam("zipcode") String zipcode) throws Exception {
    	targetService.fetchOffers(city, state, zipcode);
    	return new GenericResponse();
    }
    
    @Path("geo")
    @GET
    public GenericResponse geo() {
    	targetService.geo();
    	return new GenericResponse();
    }
}
