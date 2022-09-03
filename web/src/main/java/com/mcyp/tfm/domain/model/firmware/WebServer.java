package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;

@Data
public class WebServer {

	private List<String> apache;
	private List<String> lighttpd;
	private List<String> alphapd;
	private List<String> httpd;
	
}
