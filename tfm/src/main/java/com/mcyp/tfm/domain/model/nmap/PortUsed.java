package com.mcyp.tfm.domain.model.nmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortUsed {

	@JsonProperty("@state")
	private String state;
	
	@JsonProperty("@proto")
	private String proto;
	
	@JsonProperty("@portId")
	private String portId;
}
