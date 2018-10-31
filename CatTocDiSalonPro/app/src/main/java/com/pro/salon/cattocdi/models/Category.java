package com.pro.salon.cattocdi.models;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private List<Service> services;


    public Category() {
    }

    public Category(String id, String name, List<Service> services) {
        this.id = id;
        this.name = name;
        this.services = services;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
