package com.mcyp.tfm.domain.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceInfo {
	
    private String vendor;
    private String ip;
    private String mac;
    private List<ServicePort> openPorts;
    private String operativeSystem;
    private String connectedFrom;
    
}
