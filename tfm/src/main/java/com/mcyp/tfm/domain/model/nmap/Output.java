package com.mcyp.tfm.domain.model.nmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Output {

	@JsonProperty("@id")
	private String id;
	
	@JsonProperty("@output")
	private String output;
	
	@JsonProperty("elem")
	private Elem elem;
	
	@JsonProperty("table")
	private Table table;
}
