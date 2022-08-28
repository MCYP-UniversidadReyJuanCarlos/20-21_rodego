package com.mcyp.tfm.domain.model.dirsearch;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Dirsearch {

	@JsonProperty("info")
	private Info info;
	
	@JsonProperty("results")
	private List<Result> results;
	
}
