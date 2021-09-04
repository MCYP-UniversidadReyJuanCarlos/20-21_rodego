package com.mcyp.tfm.domain.model.nmap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperativeSystemClass {

	@JsonProperty("@type")
	private String type;
	
	@JsonProperty("@vendor")
	private String vendor;
	
	@JsonProperty("osfamily")
	private String osFamily;
	
	@JsonProperty("@osgen")
	private String osGen;
	
	@JsonProperty("@accuracy")
	private String accuracy;
	
	@JsonProperty("cpe")
	private List<String> cpe;
}
