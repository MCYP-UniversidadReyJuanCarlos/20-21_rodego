package com.mcyp.tfm.domain.model.nmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	@JsonProperty("@addr")
	private String addr;
	
	@JsonProperty("@addrtype")
	private String addrtype;
	
	@JsonProperty("@vendor")
	private String vendor;
	  
}
