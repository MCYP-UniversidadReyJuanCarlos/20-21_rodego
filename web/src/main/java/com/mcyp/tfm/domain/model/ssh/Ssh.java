package com.mcyp.tfm.domain.model.ssh;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Ssh {

	private List<Recommendation> recommendations;
	
}
