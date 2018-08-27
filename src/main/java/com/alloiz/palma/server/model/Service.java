package com.alloiz.palma.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Service extends BaseEntity<Service>{

    private String name;
    private String picturePath;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_description_id")
    private List<ServiceDescription> serviceDescriptions;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public Service setName(String name) {
        this.name = name;
        return this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public Service setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    public List<ServiceDescription> getServiceDescriptions() {
        return serviceDescriptions;
    }

    public Service setServiceDescriptions(List<ServiceDescription> serviceDescriptions) {
        this.serviceDescriptions = serviceDescriptions;
        return this;
    }

//    @Override
//    public String toString() {
//        return "Service{" +
//                "name='" + name + '\'' +
//                ", serviceDescriptions=" +
//                (serviceDescriptions == null ? "null" : serviceDescriptions) +
//                ", id=" + id +
//                ", available=" + available +
//                '}';
//    }


    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", serviceDescriptions=" +
                (serviceDescriptions == null ? "null" : serviceDescriptions) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
