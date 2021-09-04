package com.mcyp.tfm.domain.model.nmap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Port {
	
	@JsonProperty("@protocol")
	private String protocol;
	
	@JsonProperty("@portid")
	private String portId;
	
	@JsonProperty("service")
	private Service service;

	@JsonProperty("script")
	private List<Script> script;

}
