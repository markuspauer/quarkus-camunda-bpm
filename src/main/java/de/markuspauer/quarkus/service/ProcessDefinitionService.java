package de.markuspauer.quarkus.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.camunda.bpm.engine.RepositoryService;

import de.markuspauer.quarkus.model.ProcessDefinitionDTO;

@RequestScoped
public class ProcessDefinitionService {

    @Inject
    RepositoryService repositoryService;

    public List<ProcessDefinitionDTO> getProcessDefinitions() {
        return repositoryService.createProcessDefinitionQuery().latestVersion().list().stream()
                .map(entry -> new ProcessDefinitionDTO(entry.getId(), entry.getName(), entry.getDescription()))
                .collect(Collectors.toList());
    }

}
