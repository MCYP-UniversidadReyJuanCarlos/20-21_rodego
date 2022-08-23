package com.mcyp.tfm.domain.model.nmap.scan.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mcyp.tfm.domain.model.nmap.scan.Hostname;

import lombok.Data;
import lombok.experimental.Accessors;

@JsonIgnoreProperties( ignoreUnknown = true)
@Data
@Accessors(chain = true)
public class HostResult {

	private List<TcpResult> tcp;
	private List<Hostname> hostnames;
	private AddressesResult addresses;
	private VendorResult vendor;
	private StatusResult status;
}
	