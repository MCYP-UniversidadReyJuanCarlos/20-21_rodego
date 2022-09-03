package com.mcyp.tfm.domain.model.firmware;


import lombok.Data;

@Data
public class FirmwareResponse {

	private PasswordResponse password;
	private Md5Hash md5Hash;
	private Ssl ssl;
	private Ssh ssh;
	private Configuration configuration;
	private Database databas;
	private Script script;
	private Bin bin;
	private Pattern pattern;
	private WebServer webServer;
	private Binary binary;
	private Addresses addresses;
	private Url url;
	private Email email;
}
