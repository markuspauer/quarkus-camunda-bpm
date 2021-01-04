package de.markuspauer.quarkus.rest;

import java.io.ByteArrayInputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.jboss.logging.Logger;

import de.markuspauer.quarkus.service.ProcessEngineService;
import io.quarkus.vertx.http.runtime.devmode.Json;
import io.quarkus.vertx.http.runtime.devmode.Json.JsonObjectBuilder;

@Path("engine")
@RequestScoped
public class ProcessEngineEndpoint {

    @Inject
    Logger LOG;

    @Inject
    ProcessEngineService processEngineService;

    @Inject
    RepositoryService repositoryService;

    @Inject
    RuntimeService runtimeService;

    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEngineInfo() {
        JsonObjectBuilder processes = Json.object()
                .put("deployed", repositoryService.createProcessDefinitionQuery().count())
                .put("running", runtimeService.createExecutionQuery().count());
        return Json.object().put("engine", processEngineService.getName()).put("processes", processes).build();
    }

    @POST
    @Path("deployment")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response createDeployment(@HeaderParam("Deployment-Name") String name,
            @HeaderParam("Resource-Name") String resourceName, byte[] content) {
        LOG.info("Content: " + content);
        LOG.info("Name: " + name);
        try {
            repositoryService.createDeployment().name(name)
                .addInputStream(resourceName, new ByteArrayInputStream(content)).deploy();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("deployment/{id}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response updateDeployment(@PathParam("id") String deploymentId, @HeaderParam("Deployment-Name") String name,
    @HeaderParam("Resource-Name") String resourceName, byte[] content) {
        return Response.ok().build();
    }

}