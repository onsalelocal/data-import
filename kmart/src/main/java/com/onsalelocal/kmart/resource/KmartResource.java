package com.onsalelocal.kmart.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.kmart.service.KmartService;
import common.util.web.GenericResponse;
import common.util.web.JerseyBaseResource;

@Path("/ws/kmart")
@Component
public class KmartResource extends JerseyBaseResource {
    @Autowired private KmartService kmartService;

    @GET
    @Path("/stores")
    public StoreList listStores() {
    	return kmartService.listStores();
    }
    
    @GET
    @Path("/fetch-stores")
    public GenericResponse fetchStores() {
    	kmartService.fetchStores();
    	return new GenericResponse();
    }
}
