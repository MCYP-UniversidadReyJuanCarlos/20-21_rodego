package com.mcyp.tfm.domain.model.nmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Uptime {

	@JsonProperty("@seconds")
	private String seconds;
	
	@JsonProperty("@lastboot")
	private String lastBoot;
}
