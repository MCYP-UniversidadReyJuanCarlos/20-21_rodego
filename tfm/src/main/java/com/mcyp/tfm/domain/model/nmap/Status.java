package com.mcyp.tfm.domain.model.nmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

	@JsonProperty("@state")
	private String state;
	
	@JsonProperty("@reason")
	private String reason;
	
	@JsonProperty("@reason_ttl")
	private String reasonTtl;
	
}
