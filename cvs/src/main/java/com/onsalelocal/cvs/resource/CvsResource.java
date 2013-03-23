package com.onsalelocal.cvs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.cvs.service.CvsService;
import common.util.web.GenericResponse;
import common.util.web.JerseyBaseResource;

@Path("/ws/cvs")
@Component
public class CvsResource extends JerseyBaseResource {
    @Autowired private CvsService cvsService;

    @GET
    @Path("/stores")
    public StoreList listStores() {
    	return cvsService.listStores();
    }
    
    @GET
    @Path("/fetch-stores")
    public GenericResponse fetchStores() {
    	cvsService.fetchStores();
    	return new GenericResponse();
    }
}
