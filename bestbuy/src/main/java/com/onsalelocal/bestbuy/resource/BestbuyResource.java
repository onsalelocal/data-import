package com.onsalelocal.bestbuy.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.bestbuy.service.BestbuyService;
import common.util.web.GenericResponse;
import common.util.web.JerseyBaseResource;

@Path("/ws/bestbuy")
@Component
public class BestbuyResource extends JerseyBaseResource {
    @Autowired private BestbuyService bestbuyService;

    @GET
    @Path("/stores")
    public StoreList listStores() {
    	return bestbuyService.listStores();
    }
    
    @GET
    @Path("/fetch-stores")
    public GenericResponse fetchStores() {
    	bestbuyService.fetchStores();
    	return new GenericResponse();
    }
}
