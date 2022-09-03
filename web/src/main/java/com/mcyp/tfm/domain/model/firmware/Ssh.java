package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;

@Data
public class Ssh {

	List<String> authorizedKeys;
	List<String> hostKey;
	List<String> idRsa;
	List<String> idDsa;
	List<String> pub;

}
