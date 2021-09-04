package com.mcyp.tfm.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Reference {

	List<String> references = new ArrayList<String>();
}
