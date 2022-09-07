package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PasswordResponse {

	List<String> passwd;
	List<String> shadow;
	List<String> psk;
	
}
