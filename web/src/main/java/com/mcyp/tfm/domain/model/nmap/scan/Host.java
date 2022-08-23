package com.mcyp.tfm.domain.model.nmap.scan;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties( ignoreUnknown = true)
@Data
public class Host {

	private List<Hostname> hostnames;
	private Addresses addresses;
	private Vendor vendor;
	private Status status;
	private Tcp tcp;
}
	