package com.mcyp.tfm.domain.model.nmap.scan.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TcpResult {

	private String port;
	private String state;
	private String reason;
	private String name;
	private String product;
	private String version;
	private String extrainfo;
	private String conf;
	private String cpe;
	
}
