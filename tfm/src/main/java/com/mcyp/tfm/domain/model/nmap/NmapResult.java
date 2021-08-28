package com.mcyp.tfm.domain.model.nmap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NmapResult {

	@JsonProperty("nmaprun")
	private NmapRun nmapRun;
}

