package de.markuspauer.quarkus.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;

import de.markuspauer.quarkus.model.ProcessDefinitionDTO;
import de.markuspauer.quarkus.service.ProcessDefinitionService;

@Path("process")
@RequestScoped
public class ProcessDefinitionEndpoint {
    
    @Inject
    ProcessDefinitionService processDefinitionService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProcessDefinitionDTO> getAllProcessDefinitions() {
        return processDefinitionService.getProcessDefinitions();
    }

}
