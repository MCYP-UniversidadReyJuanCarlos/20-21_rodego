package com.mcyp.tfm.domain.model.nmap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Host {

	@JsonProperty("status")
	private Status status;
	
	@JsonProperty("address")
	private List<Address> addresses;

	@JsonProperty("ports")
	private Ports ports;
	
	@JsonProperty("os")
	private OperativeSystem operativeSystem;
	
}
