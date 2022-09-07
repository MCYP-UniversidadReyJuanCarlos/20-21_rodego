package com.mcyp.tfm.domain.model.dirsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Info {

	@JsonProperty("args")
	private String args;
	
	@JsonProperty("time")
	private String date;
	
}
