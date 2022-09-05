package com.mcyp.tfm.domain.model.nmap.scan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ScanResult {

	@JsonProperty("New item")
	private String newItem;
	@JsonProperty("nmap")
	private Nmap nmap;
	@JsonProperty("scan")
	private Scan scan;
}
