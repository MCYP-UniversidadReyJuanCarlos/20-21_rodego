package com.mcyp.tfm.domain.model.nmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {

	@JsonProperty("@name")
	private String name;
	
	@JsonProperty("@product")
	private String product;
	
	@JsonProperty("@servicefp")
	private String servicefp;
	
	@JsonProperty("@version")
	private String version;
	
	@JsonProperty("@method")
	private String method;
	
	@JsonProperty("@conf")
	private String conf;
	
	@JsonProperty("cpe")
	private String cpe;
	
}
