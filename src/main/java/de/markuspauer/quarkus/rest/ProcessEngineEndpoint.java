package de.markuspauer.quarkus.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import de.markuspauer.quarkus.service.ProcessEngineService;

@Path("engine")
@RequestScoped
public class ProcessEngineEndpoint {

    @Inject
    ProcessEngineService processEngineService;
    
    @GET
    public String getEngineName() {
        return processEngineService.getName();
    }

}