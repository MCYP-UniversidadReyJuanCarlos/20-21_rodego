
package com.mcyp.tfm.domain.model.testssl;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SslResponse {

    private String invocation;
    private String at;
    private String version;
    private String openssl;
    private String startTime;

    private ScanResult scanResult;
  
    List<VulnerabilityResponse> highVulns;
    List<VulnerabilityResponse> criticalVulns;
    List<VulnerabilityResponse> mediumVulns;
    List<VulnerabilityResponse> lowVulns;
    
    private Integer scanTime;

}
