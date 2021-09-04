package com.mcyp.tfm.domain.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScriptResponse {

	private List<ScriptType> scriptType;
	
	private List<VulnerabilityInfo> vulnerabilityInfoList;
}
