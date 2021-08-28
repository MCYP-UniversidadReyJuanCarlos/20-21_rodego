package com.mcyp.tfm.domain.model.nmap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperativeSystem {

	@JsonProperty("portused")
	private List<PortUsed> usedPorts;
	
	@JsonProperty("osmatch")
	private List<OperativeSystemMatch> operativeSystemMatch;
	
	@JsonProperty("uptime")
	private Uptime uptime;
}
