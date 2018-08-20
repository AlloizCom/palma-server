package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.ServiceDescription;

import java.util.List;

@Dto
public class ServiceFullDto extends ServiceShortDto<ServiceFullDto> {

    protected List<ServiceDescription> serviceDescriptions;

    public ServiceFullDto() {
    }

    public List<ServiceDescription> getServiceDescriptions() {
        return serviceDescriptions;
    }

    public ServiceFullDto setServiceDescriptions(List<ServiceDescription> serviceDescriptions) {
        this.serviceDescriptions = serviceDescriptions;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceFullDto{" +
                "serviceDescriptions=" + serviceDescriptions +
                ", id=" + id +
                ", available=" + available +
                ", name='" + name + '\'' +
                '}';
    }
}
