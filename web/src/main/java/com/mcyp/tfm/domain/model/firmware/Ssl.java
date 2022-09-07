package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Ssl {

	private List<String> crt;
	private List<String> pem;
	private List<String> cer;
	private List<String> p7b;
	private List<String> p12;
	private List<String> key;
	
}
