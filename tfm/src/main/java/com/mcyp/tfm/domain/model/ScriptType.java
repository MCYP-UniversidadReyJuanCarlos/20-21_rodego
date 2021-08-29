package com.mcyp.tfm.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScriptType {

	private String name;
	private String result;
}
