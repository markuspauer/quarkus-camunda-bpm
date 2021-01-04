package de.markuspauer.quarkus.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

@ApplicationScoped
public class LogProducer {

    @Produces
    Logger createLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getBean().getBeanClass());
    }
    
}
