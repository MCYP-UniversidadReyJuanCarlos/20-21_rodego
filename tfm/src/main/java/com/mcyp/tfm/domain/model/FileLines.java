package com.mcyp.tfm.domain.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileLines {

	private String title;
	
	private String type;
	
	private List<String> line;
}
