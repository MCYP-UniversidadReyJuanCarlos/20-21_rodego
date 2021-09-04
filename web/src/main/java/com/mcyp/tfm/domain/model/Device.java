package com.mcyp.tfm.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Device {
	
    private String vendor;
    private String ip;
    private String mac;
    
}
