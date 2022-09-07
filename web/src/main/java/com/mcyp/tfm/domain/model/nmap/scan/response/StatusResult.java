package com.mcyp.tfm.domain.model.nmap.scan.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatusResult {

    private String state;
    private String reason;
    
}
