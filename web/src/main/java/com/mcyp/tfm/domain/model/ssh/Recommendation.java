package com.mcyp.tfm.domain.model.ssh;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Recommendation {

	private String algorithm;
	private String recommendation;
}
