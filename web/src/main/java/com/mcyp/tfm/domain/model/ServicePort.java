package com.mcyp.tfm.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServicePort {

	private String service;
	
	private String port;
	
	private String protocol;
	
}
