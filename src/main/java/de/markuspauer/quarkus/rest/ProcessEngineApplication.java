package de.markuspauer.quarkus.rest;

import javax.enterprise.context.Dependent;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Dependent
@ApplicationPath("/api")
public class ProcessEngineApplication extends Application {
    
}