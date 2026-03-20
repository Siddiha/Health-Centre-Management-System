package com.healthcentre.config;

import com.healthcentre.resource.StaffResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * JAX-RS Application entry point.
 * All REST endpoints are available at: /api/*
 */
@ApplicationPath("/api")
public class HealthCentreApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(StaffResource.class);
        return classes;
    }
}
