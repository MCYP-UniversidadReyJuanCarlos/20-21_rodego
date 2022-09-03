package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;

@Data
public class PasswordResponse {

	List<String> passwd;
	List<String> shadow;
	List<String> psk;
	
}
