package de.markuspauer.quarkus.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.RepositoryService;

import de.markuspauer.quarkus.service.ProcessEngineService;
import io.quarkus.vertx.http.runtime.devmode.Json;

@Path("engine")
@RequestScoped
public class ProcessEngineEndpoint {

    @Inject
    ProcessEngineService processEngineService;

    @Inject
    RepositoryService repositoryService;
    
    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEngineInfo() {
        return Json
        .object()
        .put("engine", processEngineService.getName())
        .put("processes", repositoryService.createProcessDefinitionQuery().count())
        .build();
    }

}