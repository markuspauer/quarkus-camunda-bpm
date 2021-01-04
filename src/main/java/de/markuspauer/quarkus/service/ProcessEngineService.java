package de.markuspauer.quarkus.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class ProcessEngineService implements Serializable {

    private static final long serialVersionUID = -4428518317731241682L;

    @Inject
    Logger LOG;

    private ProcessEngine engine;

    @ConfigProperty(name = "camunda.engine.name")
    private String processEngineName;
    
    @ConfigProperty(name = "camunda.db.url")
    private String camundaDbUrl;

    @ConfigProperty(name = "camunda.db.user")
    private String camundaDbUser;

    @ConfigProperty(name = "camunda.db.pass")
    private String camundaDbPass;

    @PostConstruct
    void init(@Observes StartupEvent event) {
        engine = ProcessEngineConfiguration
        .createStandaloneProcessEngineConfiguration()
        .setProcessEngineName(processEngineName)
        .setJdbcDriver("org.h2.Driver")
        .setJdbcUrl(camundaDbUrl)
        .setJdbcUsername(camundaDbUser)
        .setJdbcPassword(camundaDbPass)
        .setDatabaseSchemaUpdate("true")
        .buildProcessEngine();
    }

    public String getName() {
        return engine.getName();
    }

    @Produces
    RepositoryService getRepositoryService() {
        return engine.getRepositoryService();
    }

    @Produces
    RuntimeService getRuntimeService() {
        return engine.getRuntimeService();
    }
    
}