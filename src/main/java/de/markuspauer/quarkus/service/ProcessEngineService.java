package de.markuspauer.quarkus.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;

@ApplicationScoped
public class ProcessEngineService implements Serializable {

    private ProcessEngine engine;

    @PostConstruct
    void init() {
        engine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();
    }

    public String getName() {
        return engine.getName();
    }
    
}