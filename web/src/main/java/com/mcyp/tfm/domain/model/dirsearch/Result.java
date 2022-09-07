package com.mcyp.tfm.domain.model.dirsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Result {

	@JsonProperty("content-length")
	private Integer contentLength;
	
	@JsonProperty("content-type")
	private String contentType;
	
	@JsonProperty("redirect")
	private String redirect;
	
	@JsonProperty("status")
	private Integer status;
	
	@JsonProperty("url")
	private String url;
	
}
