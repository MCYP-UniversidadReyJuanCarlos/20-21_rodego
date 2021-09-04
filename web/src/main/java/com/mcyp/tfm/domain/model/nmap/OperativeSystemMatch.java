package com.mcyp.tfm.domain.model.nmap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class OperativeSystemMatch {

	@JsonProperty("@name")
	private String name;
	
	@JsonProperty("@accuracy")
	private String accuracy;
	
	@JsonProperty("@line")
	private String line;
	
	@JsonProperty("osclass")
	private List<OperativeSystemClass> osClass;
	
}
