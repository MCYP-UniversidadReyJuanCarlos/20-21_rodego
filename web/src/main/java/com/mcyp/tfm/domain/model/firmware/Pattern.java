package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pattern {

	private List<String> upgrade;
	private List<String> admin;
	private List<String> root;
	private List<String> password;
	private List<String> passwd;
	private List<String> pwd;
	private List<String> dropbear;
	private List<String> ssl;
	private List<String> privateKey;
	private List<String> telnet;
	private List<String> secret;
	private List<String> pgp;
	private List<String> gpg;
	private List<String> token;
	private List<String> apiKey;
	private List<String> oauth;
	
}
