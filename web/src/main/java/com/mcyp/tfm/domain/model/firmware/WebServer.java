package com.mcyp.tfm.domain.model.firmware;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WebServer {

	private boolean apache;
	private boolean lighttpd;
	private boolean alphapd;
	private boolean httpd;
	
}
